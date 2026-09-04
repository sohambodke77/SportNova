package com.sportnova.app.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.theme.*

/**
 * 4-Step Registration Progress Stepper
 * Step 1: Personal Info
 * Step 2: Sport Details
 * Step 3: Team Details
 * Step 4: Review Registration
 */
@Composable
fun RegistrationProgressIndicator(
    currentStep: Int,
    totalSteps: Int = 4,
    modifier: Modifier = Modifier
) {
    val stepTitles = listOf("Personal", "Sport", "Team", "Review")

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 1..totalSteps) {
            val isCompleted = step < currentStep
            val isCurrent = step == currentStep

            val circleBg by animateColorAsState(
                targetValue = when {
                    isCompleted -> VictoryGreen
                    isCurrent -> ElectricBlue
                    else -> Color(0x331E293B)
                },
                label = "circleBg"
            )

            val circleBorder by animateColorAsState(
                targetValue = when {
                    isCompleted -> VictoryGreen
                    isCurrent -> NeonCyan
                    else -> GlassBorderLight
                },
                label = "circleBorder"
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(circleBg)
                        .border(1.5.dp, circleBorder, CircleShape)
                ) {
                    if (isCompleted) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Completed",
                            tint = BackgroundDark,
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Text(
                            text = "$step",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isCurrent) Color.White else TextSecondary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stepTitles.getOrElse(step - 1) { "Step $step" },
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                        color = if (isCurrent) NeonCyan else TextSecondary
                    )
                )
            }

            // Connecting line between steps
            if (step < totalSteps) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .padding(horizontal = 6.dp)
                        .background(if (step < currentStep) VictoryGreen else Color(0x33334155))
                )
            }
        }
    }
}
