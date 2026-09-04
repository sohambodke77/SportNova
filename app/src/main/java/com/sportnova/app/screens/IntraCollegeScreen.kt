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
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Shield
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
import com.sportnova.app.components.EventCard
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.SectionHeader
import com.sportnova.app.components.SportNovaBadge
import com.sportnova.app.model.*
import com.sportnova.app.theme.*

/**
 * Screen 3: Intra College Competitions
 * Competitions happening inside the student's own college with Department Shield & Department Leaderboards.
 */
@Composable
fun IntraCollegeScreen(
    onEventClick: (SportEvent) -> Unit,
    onRegisterClick: (SportEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val intraEvents = MockSportsData.allEvents.filter { it.competitionType == CompetitionType.INTRA_COLLEGE }
    val departmentRankings = MockSportsData.departmentRankings

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 90.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Title and Subtitle
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Intra College",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 28.sp,
                    color = TextPrimary
                )
            )
            Text(
                text = "Compete. Represent. Rise.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 1. Featured College Identity Banner
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
                            listOf(Color(0xFF0F1F38), Color(0xFF091220))
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = NeonCyan,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "MIT AOE CAMPUS",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = NeonCyan,
                                    letterSpacing = 0.8.sp
                                )
                            )
                        }

                        SportNovaBadge(
                            text = "DEPARTMENT SHIELD",
                            borderColor = GoldMedal,
                            textColor = GoldMedal,
                            backgroundColor = GoldMedal.copy(alpha = 0.15f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Annual Department Sports Shield 2026",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp,
                            color = TextPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "5 Academic Departments • 8 Sporting Disciplines • 1 Champion",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Department vs Department Clashes
        SectionHeader(title = "Department vs Department Clashes")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DepartmentClashCard(
                dept1 = "Computer Engineering",
                dept2 = "Mechanical Engineering",
                sport = "Soccer Semifinal",
                time = "Tomorrow 04:00 PM",
                venue = "Main Football Ground"
            )
            DepartmentClashCard(
                dept1 = "Electronics & Telecomm",
                dept2 = "Civil Engineering",
                sport = "Basketball Quarters",
                time = "Friday 05:30 PM",
                venue = "Indoor Court 2"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. College Department Leaderboard
        SectionHeader(title = "Department Standings")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            departmentRankings.forEach { dept ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0x330F1D32))
                        .border(1.dp, GlassBorderLight, RoundedCornerShape(16.dp))
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "#${dept.rank}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (dept.rank == 1) GoldMedal else TextSecondary
                            ),
                            modifier = Modifier.width(32.dp)
                        )
                        Column {
                            Text(
                                text = dept.departmentName,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = TextPrimary
                                )
                            )
                            Text(
                                text = "🥇 ${dept.gold}  🥈 ${dept.silver}  🥉 ${dept.bronze}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            )
                        }
                    }

                    Text(
                        text = "${dept.points} pts",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = NeonCyan,
                            fontSize = 13.sp
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Intra Tournaments List
        SectionHeader(title = "Active Intra Tournaments")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            intraEvents.forEach { event ->
                EventCard(
                    event = event,
                    onViewDetails = onEventClick,
                    onRegisterClick = onRegisterClick
                )
            }
        }
    }
}

@Composable
private fun DepartmentClashCard(
    dept1: String,
    dept2: String,
    sport: String,
    time: String,
    venue: String
) {
    GlassCard(
        cornerRadius = 18.dp,
        borderStrokeColor = GlassBorderLight,
        backgroundColor = Color(0x550B1628)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sport,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = NeonCyan
                    )
                )
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = EnergyOrange,
                        fontSize = 11.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dept1,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = TextPrimary
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "VS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = TextSecondary
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = dept2,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = TextPrimary
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "📍 $venue",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 10.sp
                )
            )
        }
    }
}
