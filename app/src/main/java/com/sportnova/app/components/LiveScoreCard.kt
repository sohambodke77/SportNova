package com.sportnova.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.model.LiveMatch
import com.sportnova.app.theme.*

/**
 * Match Intelligence & Live Tracker Card
 * Exact match for Screen 1 "Today's Match Intelligence" with AI insights badge,
 * athlete cutout, and frosted Score & Time metric bubbles.
 */
@Composable
fun LiveScoreCard(
    match: LiveMatch,
    onViewLiveDetails: (LiveMatch) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xD90E1B2E),
                        Color(0xEE09111D)
                    )
                )
            )
            .border(1.dp, GlassBorderBlue, RoundedCornerShape(26.dp))
            .clickable { onViewLiveDetails(match) }
            .padding(18.dp)
    ) {
        // Atmospheric radial back-glow
        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.CenterStart)
                .background(
                    Brush.radialGradient(
                        listOf(Color(0x330066FF), Color.Transparent)
                    )
                )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Side: Athlete silhouette visual + Live Headline
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(170.dp)
            ) {
                // Procedural player graphic
                AthleteSilhouetteVisual(
                    modifier = Modifier
                        .size(140.dp)
                        .align(Alignment.CenterStart),
                    sportType = match.sport,
                    accentGlowColor = ElectricBlue
                )

                // Big Headline at bottom left: "Today's Match Intelligence"
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 2.dp)
                ) {
                    Text(
                        text = "Today's Match\nIntelligence",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            color = TextPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${match.team1Name} vs ${match.team2Name}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            color = NeonCyan
                        )
                    )
                }
            }

            // Right Side: ✨ AI Insights badge & Metric Bubbles
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(170.dp)
                    .padding(vertical = 4.dp)
            ) {
                // Top Right: ✨ AI Insights badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.White)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color(0xFF0F172A),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "AI Insights",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 11.sp,
                                color = Color(0xFF0F172A)
                            )
                        )
                    }
                }

                // Middle: Stacked Glass Metric Bubbles
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    GlassMetricBubble(
                        label = "Score",
                        value = "${match.team1Score}-${match.team2Score}"
                    )
                    GlassMetricBubble(
                        label = "Time",
                        value = match.timeRemaining
                    )
                }
            }
        }
    }
}
