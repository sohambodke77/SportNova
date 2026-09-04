package com.sportnova.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.sportnova.app.components.AchievementCard
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.EventCard
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.ProfileStatCard
import com.sportnova.app.model.Achievement
import com.sportnova.app.model.AthleteProfile
import com.sportnova.app.model.MockSportsData
import com.sportnova.app.model.SportEvent
import com.sportnova.app.theme.*

/**
 * Screen 7: Athlete Profile Screen
 * Sports Identity Card, athletic stat cards, and Overview/My Events/Achievements tabs.
 */
@Composable
fun ProfileScreen(
    athlete: AthleteProfile,
    onAchievementClick: (Achievement) -> Unit,
    onEventClick: (SportEvent) -> Unit,
    onNavigateToCertificates: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Overview", "My Events", "Achievements")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 90.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Top Actions (Edit Profile & Share)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Athlete Profile",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    color = TextPrimary
                )
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CircularIconButton(
                    icon = Icons.Default.Edit,
                    contentDescription = "Edit Profile",
                    onClick = { },
                    size = 40.dp
                )
                CircularIconButton(
                    icon = Icons.Default.Share,
                    contentDescription = "Share Profile",
                    onClick = { },
                    size = 40.dp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==========================================
        // SPORTS IDENTITY CARD
        // ==========================================
        GlassCard(
            cornerRadius = 24.dp,
            borderStrokeColor = GlassBorderBlue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF0F1E36), Color(0xFF07101E))
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Profile Avatar with glowing rim
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1E3A8A))
                                .border(2.dp, NeonCyan, CircleShape)
                        ) {
                            Text(
                                text = athlete.studentName.take(2).uppercase(),
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = athlete.studentName,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Black,
                                    fontSize = 18.sp,
                                    color = TextPrimary
                                )
                            )
                            Text(
                                text = "${athlete.collegeName} • ${athlete.academicYear}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${athlete.primarySport.iconEmoji} ${athlete.primarySport.displayName} • ${athlete.athleteLevel}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = NeonCyan
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Athlete ID bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0x330A1322))
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "FEDERATION ATHLETE ID",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                color = TextSecondary
                            )
                        )
                        Text(
                            text = athlete.studentId,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==========================================
        // ATHLETIC STATISTICS GRID (5 Stats)
        // ==========================================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ProfileStatCard(
                    value = "${athlete.eventsParticipated}",
                    label = "Events Entered",
                    icon = Icons.Default.EmojiEvents,
                    accentColor = ElectricBlue,
                    modifier = Modifier.weight(1f)
                )
                ProfileStatCard(
                    value = "${athlete.matchesPlayed}",
                    label = "Matches Played",
                    icon = Icons.Default.SportsBasketball,
                    accentColor = NeonCyan,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ProfileStatCard(
                    value = "${athlete.wins}",
                    label = "Victories",
                    icon = Icons.Default.CheckCircle,
                    accentColor = VictoryGreen,
                    modifier = Modifier.weight(1f)
                )
                ProfileStatCard(
                    value = "%,d".format(athlete.totalPoints),
                    label = "Points Earned",
                    icon = Icons.Default.Star,
                    accentColor = EnergyOrange,
                    modifier = Modifier.weight(1f)
                )
                ProfileStatCard(
                    value = "#${athlete.collegeRank}",
                    label = "College Rank",
                    icon = Icons.Default.MilitaryTech,
                    accentColor = GoldMedal,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ==========================================
        // PROFILE TABS: Overview, My Events, Achievements
        // ==========================================
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tabs.forEachIndexed { index, tab ->
                val isSelected = selectedTab == index
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(50.dp))
                        .background(if (isSelected) Color.White else Color(0x3314223A))
                        .border(1.dp, if (isSelected) Color.White else GlassBorderLight, RoundedCornerShape(50.dp))
                        .clickable { selectedTab = index }
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

        Spacer(modifier = Modifier.height(18.dp))

        // Tab Content
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            when (selectedTab) {
                0 -> {
                    // Overview: Bio + Certificates CTA
                    Column {
                        GlassCard(
                            cornerRadius = 20.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Athlete Scouting Bio",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = athlete.bio,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = TextSecondary,
                                        lineHeight = 20.sp
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Verified Certificates Banner
                        GlassCard(
                            cornerRadius = 20.dp,
                            borderStrokeColor = GoldMedal.copy(alpha = 0.4f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigateToCertificates() }
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = null,
                                        tint = GoldMedal,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = "Verified Sports Certificates",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = TextPrimary
                                            )
                                        )
                                        Text(
                                            text = "3 Official Credentials Awarded",
                                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                                        )
                                    }
                                }

                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = null,
                                    tint = TextSecondary
                                )
                            }
                        }
                    }
                }
                1 -> {
                    // My Events
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        MockSportsData.allEvents.take(3).forEach { event ->
                            EventCard(
                                event = event,
                                onViewDetails = onEventClick,
                                onRegisterClick = { }
                            )
                        }
                    }
                }
                2 -> {
                    // Achievements List
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        MockSportsData.achievements.forEach { achievement ->
                            AchievementCard(
                                achievement = achievement,
                                onClick = { onAchievementClick(achievement) }
                            )
                        }
                    }
                }
            }
        }
    }
}
