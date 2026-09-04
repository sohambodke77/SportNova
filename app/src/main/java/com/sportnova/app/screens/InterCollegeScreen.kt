package com.sportnova.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.SectionHeader
import com.sportnova.app.components.TournamentCard
import com.sportnova.app.model.CompetitionType
import com.sportnova.app.model.MockSportsData
import com.sportnova.app.model.SportEvent
import com.sportnova.app.theme.*

/**
 * Screen 4: Inter College Competitions
 * State & University championships, participating colleges, and University Rankings.
 */
@Composable
fun InterCollegeScreen(
    onEventClick: (SportEvent) -> Unit,
    onRegisterClick: (SportEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val interEvents = MockSportsData.allEvents.filter {
        it.competitionType == CompetitionType.INTER_COLLEGE || it.competitionType == CompetitionType.STATE_VARSITY
    }
    val collegeRankings = MockSportsData.collegeRankings

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
                text = "Inter College",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 28.sp,
                    color = TextPrimary
                )
            )
            Text(
                text = "Represent Your College.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // University Championships Showcase Cards
        SectionHeader(title = "Featured Championships")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            interEvents.forEach { event ->
                TournamentCard(
                    event = event,
                    participatingCollegesCount = 24,
                    sportsCount = 8,
                    onRegisterClick = onRegisterClick
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // College Rankings Table
        SectionHeader(title = "University College Rankings")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            collegeRankings.forEach { college ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0x330E192D))
                        .border(1.dp, GlassBorderLight, RoundedCornerShape(18.dp))
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val rankColor = when (college.rank) {
                            1 -> GoldMedal
                            2 -> SilverMedal
                            3 -> BronzeMedal
                            else -> TextSecondary
                        }
                        Text(
                            text = "#${college.rank}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = rankColor
                            ),
                            modifier = Modifier.width(34.dp)
                        )

                        Column {
                            Text(
                                text = college.collegeName,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = TextPrimary
                                )
                            )
                            Text(
                                text = "🥇 ${college.gold}  🥈 ${college.silver}  🥉 ${college.bronze} • ${college.winRate} Wins",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            )
                        }
                    }

                    Text(
                        text = "${college.points} pts",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = NeonCyan,
                            fontSize = 13.sp
                        )
                    )
                }
            }
        }
    }
}
