package com.sportnova.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import com.sportnova.app.model.Achievement
import com.sportnova.app.model.AchievementTier
import com.sportnova.app.theme.*

/**
 * Achievement Badge Card
 * Displays gold, silver, bronze medals, MVP trophies, and university representative honors.
 */
@Composable
fun AchievementCard(
    achievement: Achievement,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (tierColor, icon) = when (achievement.tier) {
        AchievementTier.GOLD -> GoldMedal to Icons.Default.EmojiEvents
        AchievementTier.SILVER -> SilverMedal to Icons.Default.MilitaryTech
        AchievementTier.BRONZE -> BronzeMedal to Icons.Default.MilitaryTech
        AchievementTier.MVP -> NeonCyan to Icons.Default.Star
        AchievementTier.WINNER -> EnergyOrange to Icons.Default.EmojiEvents
        AchievementTier.REPRESENTATIVE -> ElectricBlue to Icons.Default.MilitaryTech
    }

    GlassCard(
        cornerRadius = 20.dp,
        borderStrokeColor = tierColor.copy(alpha = 0.35f),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Left: Medal/Trophy Icon inside glowing circular container
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(tierColor.copy(alpha = 0.15f))
                    .border(1.5.dp, tierColor.copy(alpha = 0.6f), CircleShape)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tierColor,
                    modifier = Modifier.size(24.dp)
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
                        text = achievement.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )

                    Text(
                        text = "+${achievement.pointsAwarded} pts",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = tierColor
                        )
                    )
                }

                Text(
                    text = achievement.eventName,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = NeonCyan,
                        fontSize = 11.sp
                    )
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = achievement.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}
