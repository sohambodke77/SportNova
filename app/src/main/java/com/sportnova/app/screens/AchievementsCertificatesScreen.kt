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
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.GlassPillButton
import com.sportnova.app.components.SectionHeader
import com.sportnova.app.model.Achievement
import com.sportnova.app.model.DigitalCertificate
import com.sportnova.app.model.MockSportsData
import com.sportnova.app.theme.*

/**
 * Screen 8: Certificates & Achievements Screen
 * Trophies showcase, Champion level progress tier, and verified digital certificates.
 */
@Composable
fun AchievementsCertificatesScreen(
    onBackClick: () -> Unit,
    onCertificateClick: (DigitalCertificate) -> Unit,
    modifier: Modifier = Modifier
) {
    val achievements = MockSportsData.achievements
    val certificates = MockSportsData.certificates

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 90.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularIconButton(
                icon = Icons.Default.ArrowBack,
                contentDescription = "Back",
                onClick = onBackClick,
                size = 40.dp
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "My Achievements",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    color = TextPrimary
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Champion Progress Card
        GlassCard(
            cornerRadius = 22.dp,
            borderStrokeColor = GlassBorderBlue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF0C192E), Color(0xFF070F1C))
                        )
                    )
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.MilitaryTech,
                            contentDescription = null,
                            tint = GoldMedal,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "TIER: PLATINUM ATHLETE",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldMedal,
                                letterSpacing = 0.8.sp
                            )
                        )
                    }

                    Text(
                        text = "1,840 / 2,500 pts",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = NeonCyan
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "3 More Events to reach Sports Champion Level",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Progress Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0x331E293B))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.74f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(ElectricBlue, NeonCyan, GoldMedal)
                                )
                            )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Digital Certificates Section
        SectionHeader(title = "Verified Digital Certificates")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            certificates.forEach { cert ->
                GlassCard(
                    cornerRadius = 20.dp,
                    borderStrokeColor = GoldMedal.copy(alpha = 0.35f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCertificateClick(cert) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Certificate gold icon box
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0x33FFA000))
                                .border(1.dp, GoldMedal, RoundedCornerShape(14.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = GoldMedal,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = cert.rankOrRole,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = TextPrimary
                                    )
                                )
                                Text(
                                    text = "VERIFIED ✓",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Black,
                                        color = VictoryGreen,
                                        fontSize = 10.sp
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = cert.eventName,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = NeonCyan,
                                    fontSize = 11.sp
                                ),
                                maxLines = 1
                            )

                            Text(
                                text = "Issued: ${cert.dateIssued}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 10.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                GlassPillButton(
                                    text = "View Certificate",
                                    onClick = { onCertificateClick(cert) }
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Medals & Trophies List
        SectionHeader(title = "Trophy Cabinet & Medals")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            achievements.forEach { achievement ->
                AchievementCard(
                    achievement = achievement,
                    onClick = { }
                )
            }
        }
    }
}
