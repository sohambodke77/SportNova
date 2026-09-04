package com.sportnova.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.AthleteSilhouetteVisual
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.SportNovaBadge
import com.sportnova.app.components.WhitePillButton
import com.sportnova.app.model.EventStatus
import com.sportnova.app.model.SportEvent
import com.sportnova.app.theme.*

/**
 * Screen 5: Event Details Screen
 * Hero banner, schedule timeline, rules, eligibility, participating colleges, and sticky bottom Register button.
 */
@Composable
fun EventDetailsScreen(
    event: SportEvent,
    onBackClick: () -> Unit,
    onRegisterClick: (SportEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSectionIndex by remember { mutableIntStateOf(0) }
    val sectionTabs = listOf("About", "Schedule", "Rules", "Colleges")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp) // Space for sticky bottom register button
        ) {
            // ==========================================
            // HERO BANNER SECTION
            // ==========================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                // Background Gradient with ambient glow
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color(0xFF0F203D),
                                    Color(0xFF0A1324),
                                    BackgroundDark
                                )
                            )
                        )
                )

                // Athletic visual
                AthleteSilhouetteVisual(
                    modifier = Modifier
                        .size(240.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = 20.dp, y = 10.dp),
                    sportType = event.sport,
                    accentGlowColor = ElectricBlue
                )

                // Top Navigation Bar (Back & Share)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                        .align(Alignment.TopCenter),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularIconButton(
                        icon = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        onClick = onBackClick,
                        size = 42.dp
                    )

                    CircularIconButton(
                        icon = Icons.Default.Share,
                        contentDescription = "Share",
                        onClick = { },
                        size = 42.dp
                    )
                }

                // Event Badges & Title at bottom of hero
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SportNovaBadge(
                            text = "${event.sport.iconEmoji} ${event.sport.displayName}",
                            borderColor = GlassBorderBlue,
                            textColor = NeonCyan,
                            backgroundColor = Color(0x661E3A8A)
                        )
                        SportNovaBadge(
                            text = event.status.label.uppercase(),
                            borderColor = EnergyOrange,
                            textColor = EnergyOrange,
                            backgroundColor = EnergyOrange.copy(alpha = 0.2f)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 24.sp,
                            lineHeight = 30.sp,
                            color = TextPrimary
                        )
                    )
                }
            }

            // Key Info Bar (Date, Venue, Fee)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0x40111E33))
                    .border(1.dp, GlassBorderLight, RoundedCornerShape(18.dp))
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = event.dateRange,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .height(26.dp)
                        .width(1.dp)
                        .background(GlassBorderLight)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = EnergyOrange,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "MIT Sports Arena",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .height(26.dp)
                        .width(1.dp)
                        .background(GlassBorderLight)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "ENTRY",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            color = TextSecondary
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "FREE",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = VictoryGreen
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // TAB SELECTOR PILLS
            // ==========================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                sectionTabs.forEachIndexed { index, tab ->
                    val isSelected = selectedSectionIndex == index
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(50.dp))
                            .background(if (isSelected) Color.White else Color(0x33121F35))
                            .border(1.dp, if (isSelected) Color.White else GlassBorderLight, RoundedCornerShape(50.dp))
                            .clickable { selectedSectionIndex = index }
                            .padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = tab,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 12.sp,
                                color = if (isSelected) Color(0xFF070A10) else TextSecondary
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // DYNAMIC SECTION CONTENT
            // ==========================================
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                when (selectedSectionIndex) {
                    0 -> {
                        // About Section
                        Column {
                            Text(
                                text = "About This Event",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = event.description,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextSecondary,
                                    lineHeight = 21.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Eligibility & Requirements",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = event.eligibility,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextSecondary,
                                    lineHeight = 21.sp
                                )
                            )
                        }
                    }
                    1 -> {
                        // Event Schedule Timeline
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            event.scheduleTimeline.forEach { item ->
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(Color(0x330E192D))
                                        .border(1.dp, GlassBorderLight, RoundedCornerShape(14.dp))
                                        .padding(14.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = NeonCyan,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = item.timeLabel,
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = NeonCyan
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = item.activity,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = TextPrimary
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                    2 -> {
                        // Rules & Guidelines
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            event.rules.forEachIndexed { i, rule ->
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(Color(0x330E192D))
                                        .padding(12.dp)
                                ) {
                                    Text(
                                        text = "${i + 1}.",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = EnergyOrange
                                        ),
                                        modifier = Modifier.width(22.dp)
                                    )
                                    Text(
                                        text = rule,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = TextSecondary,
                                            lineHeight = 19.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                    3 -> {
                        // Participating Colleges
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            event.participatingColleges.forEach { college ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(Color(0x330E192D))
                                        .padding(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .clip(CircleShape)
                                            .background(NeonCyan)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = college,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            color = TextPrimary
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // ==========================================
        // STICKY BOTTOM ACTION BAR: "Register for Event"
        // ==========================================
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0x0006090F), Color(0xF206090F), Color(0xFF06090F))
                    )
                )
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            WhitePillButton(
                text = "Register for Event",
                onClick = { onRegisterClick(event) }
            )
        }
    }
}
