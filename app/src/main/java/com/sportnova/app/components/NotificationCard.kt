package com.sportnova.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.SportsBasketball
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
import com.sportnova.app.model.AppNotification
import com.sportnova.app.model.NotificationCategory
import com.sportnova.app.theme.*

/**
 * Modern Notification Card
 * Unread glowing indicator dot, category icon, and quick action button
 */
@Composable
fun NotificationCard(
    notification: AppNotification,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (icon, iconTint) = when (notification.category) {
        NotificationCategory.EVENTS -> Icons.Default.Campaign to ElectricBlue
        NotificationCategory.REGISTRATION -> Icons.Default.CheckCircle to VictoryGreen
        NotificationCategory.MATCHES -> Icons.Default.SportsBasketball to EnergyOrange
        NotificationCategory.ACHIEVEMENTS -> Icons.Default.EmojiEvents to GoldMedal
        NotificationCategory.ALL -> Icons.Default.Campaign to NeonCyan
    }

    GlassCard(
        cornerRadius = 20.dp,
        borderStrokeColor = if (!notification.isRead) GlassBorderBlue else GlassBorderLight,
        backgroundColor = if (!notification.isRead) Color(0xD9101C30) else SurfaceCardGlass,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Unread Dot + Icon Container
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(iconTint.copy(alpha = 0.15f))
                    .border(1.dp, iconTint.copy(alpha = 0.4f), CircleShape)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )

                if (!notification.isRead) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(LivePulsingRed)
                            .border(1.5.dp, BackgroundDark, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notification.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = if (!notification.isRead) FontWeight.Bold else FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    )

                    Text(
                        text = notification.timeAgo,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                )

                if (notification.actionText != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0x332563EB))
                            .border(1.dp, GlassBorderBlue, RoundedCornerShape(50.dp))
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = notification.actionText,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = NeonCyan,
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
