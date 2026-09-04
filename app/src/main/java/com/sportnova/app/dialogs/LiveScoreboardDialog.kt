package com.sportnova.app.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.SportNovaBadge
import com.sportnova.app.model.LiveMatch
import com.sportnova.app.theme.*

/**
 * Live Match Interactive Scoreboard Modal
 * Shows live play-by-play event timeline, quarter scores, and venue info.
 */
@Composable
fun LiveScoreboardDialog(
    match: LiveMatch,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        GlassCard(
            cornerRadius = 28.dp,
            borderStrokeColor = GlassBorderBlue,
            backgroundColor = Color(0xF209111D),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Top Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SportNovaBadge(
                        text = "● LIVE NOW (${match.timeRemaining})",
                        borderColor = LivePulsingRed,
                        textColor = LivePulsingRed,
                        backgroundColor = LivePulsingRed.copy(alpha = 0.15f),
                        isPulsing = true
                    )

                    CircularIconButton(
                        icon = Icons.Default.Close,
                        contentDescription = "Close",
                        onClick = onDismiss,
                        size = 36.dp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = match.tournamentName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = NeonCyan
                    )
                )

                Text(
                    text = match.venue,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Scoreboard Banner: Team 1 vs Team 2
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0x4016253C))
                        .border(1.dp, GlassBorderBlue, RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Team 1
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(ElectricBlue.copy(alpha = 0.2f))
                                .border(1.5.dp, ElectricBlue, CircleShape)
                        ) {
                            Text(
                                text = match.team1Name.take(3).uppercase(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = match.team1Name,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            ),
                            maxLines = 1
                        )
                        Text(
                            text = "${match.team1Score}",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 34.sp,
                                color = TextPrimary
                            )
                        )
                    }

                    // VS / Period Divider
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "VS",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextSecondary
                            )
                        )
                        Text(
                            text = match.timeRemaining,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = EnergyOrange
                            )
                        )
                    }

                    // Team 2
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(EnergyOrange.copy(alpha = 0.2f))
                                .border(1.5.dp, EnergyOrange, CircleShape)
                        ) {
                            Text(
                                text = match.team2Name.take(3).uppercase(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = match.team2Name,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            ),
                            maxLines = 1
                        )
                        Text(
                            text = "${match.team2Score}",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 34.sp,
                                color = TextPrimary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Live Play-by-Play Intelligence",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Play-by-Play Timeline
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(match.matchEvents) { event ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0x22132034))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = event.timestamp,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = NeonCyan
                                ),
                                modifier = Modifier.width(42.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = event.description,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        fontSize = 12.sp
                                    )
                                )
                                if (event.pointsAdded > 0) {
                                    Text(
                                        text = "+${event.pointsAdded} Points for ${event.scoringTeam}",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = VictoryGreen,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
