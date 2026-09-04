package com.sportnova.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sportnova.app.model.LeaderboardEntry
import com.sportnova.app.theme.*

/**
 * Leaderboard Row Item
 * Matches Screen 1 bottom leaderboard strip: Rank, Avatar, Name, Points
 */
@Composable
fun LeaderboardItem(
    entry: LeaderboardEntry,
    onItemClick: (LeaderboardEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0x330E1A2C))
            .border(1.dp, GlassBorderLight, RoundedCornerShape(18.dp))
            .clickable { onItemClick(entry) }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Left: Rank Number & Avatar & Name
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rank number with podium color
            val rankColor = when (entry.rank) {
                1 -> GoldMedal
                2 -> SilverMedal
                3 -> BronzeMedal
                else -> TextSecondary
            }

            Text(
                text = "${entry.rank}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = rankColor
                ),
                modifier = Modifier.width(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Circular avatar placeholder with glowing ring
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1E293B))
                    .border(1.5.dp, rankColor.copy(alpha = 0.8f), CircleShape)
            ) {
                Text(
                    text = entry.athleteName.take(2).uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = entry.athleteName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )
                )
                Text(
                    text = "${entry.collegeName} • ${entry.sport.displayName}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                )
            }
        }

        // Right: Total Points value
        Text(
            text = "%,d".format(entry.points),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = TextPrimary
            )
        )
    }
}
