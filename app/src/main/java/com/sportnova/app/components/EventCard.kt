package com.sportnova.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
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
import com.sportnova.app.model.CompetitionType
import com.sportnova.app.model.EventStatus
import com.sportnova.app.model.SportEvent
import com.sportnova.app.theme.*

/**
 * Reusable SportNova Event Card
 * Modern sports-tech card with glass border, status pill, participant count, and CTA
 */
@Composable
fun EventCard(
    event: SportEvent,
    onViewDetails: (SportEvent) -> Unit,
    onRegisterClick: (SportEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val statusColor = when (event.status) {
        EventStatus.LIVE -> LivePulsingRed
        EventStatus.CLOSING_SOON -> EnergyOrange
        EventStatus.REGISTRATION_OPEN -> VictoryGreen
        EventStatus.UPCOMING -> ElectricBlue
        EventStatus.COMPLETED -> TextSecondary
    }

    GlassCard(
        cornerRadius = 24.dp,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onViewDetails(event) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top Row: Sport Badge + Competition Type + Status Pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Sport tag
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0x332563EB))
                            .border(1.dp, GlassBorderBlue, RoundedCornerShape(50.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${event.sport.iconEmoji} ${event.sport.displayName}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = NeonCyan
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    val typeLabel = when (event.competitionType) {
                        CompetitionType.INTRA_COLLEGE -> "Intra"
                        CompetitionType.INTER_COLLEGE -> "Inter-Varsity"
                        CompetitionType.STATE_VARSITY -> "State"
                        CompetitionType.NATIONAL_INVITATIONAL -> "National"
                    }

                    Text(
                        text = "• $typeLabel",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 11.sp
                        )
                    )
                }

                // Status Badge
                SportNovaBadge(
                    text = event.status.label.uppercase(),
                    borderColor = statusColor,
                    textColor = statusColor,
                    backgroundColor = statusColor.copy(alpha = 0.15f),
                    isPulsing = event.status == EventStatus.LIVE
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Event Title
            Text(
                text = event.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = TextPrimary
                )
            )

            // Organizer College
            Text(
                text = "Host: ${event.organizerCollege}",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Key metadata: Date & Location
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = NeonCyan,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = event.dateRange,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextPrimary,
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = EnergyOrange,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = event.location,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 11.sp
                    ),
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Progress bar for registrations
            val progress = (event.participantsCount.toFloat() / event.maxParticipants.toFloat()).coerceIn(0f, 1f)
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Group,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${event.participantsCount}/${event.maxParticipants} Registered",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        )
                    }

                    Text(
                        text = event.registrationDeadline,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (event.status == EventStatus.CLOSING_SOON) EnergyOrange else TextSecondary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0x331E293B))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(ElectricBlue, NeonCyan)
                                )
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action Row: View Details (Glass) + Register (White Pill or Cyan)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlassPillButton(
                    text = "View Details",
                    onClick = { onViewDetails(event) },
                    modifier = Modifier.weight(1f)
                )

                if (event.status != EventStatus.COMPLETED) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .height(38.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.White)
                            .clickable { onRegisterClick(event) }
                    ) {
                        Text(
                            text = "Register Now",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF070A10),
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
