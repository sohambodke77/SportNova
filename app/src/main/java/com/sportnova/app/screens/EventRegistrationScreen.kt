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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.RegistrationProgressIndicator
import com.sportnova.app.components.WhitePillButton
import com.sportnova.app.model.SportEvent
import com.sportnova.app.model.SportType
import com.sportnova.app.theme.*

/**
 * Screen 6: Multi-Step Event Registration Screen
 * 4-Step Registration Experience:
 * Step 1: Personal Info
 * Step 2: Sports Details
 * Step 3: Team Details
 * Step 4: Review Registration
 * Success State: "You're In! 🎉" Pass with dynamic Registration ID and QR code.
 */
@Composable
fun EventRegistrationScreen(
    event: SportEvent,
    onBackClick: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentStep by remember { mutableIntStateOf(1) }
    var isRegistrationComplete by remember { mutableStateOf(false) }

    // Form fields
    var fullName by remember { mutableStateOf("Sanket Bodke") }
    var collegeName by remember { mutableStateOf("MIT Academy of Engineering") }
    var department by remember { mutableStateOf("Computer Engineering") }
    var year by remember { mutableStateOf("3rd Year") }
    var contactNumber by remember { mutableStateOf("+91 98765 43210") }

    var selectedSport by remember { mutableStateOf(event.sport) }
    var isTeamParticipation by remember { mutableStateOf(true) }
    var positionRole by remember { mutableStateOf("Point Guard / Captain") }

    var teamName by remember { mutableStateOf("MIT AOE Titans") }
    var squadSize by remember { mutableStateOf("12 Members") }
    var coachName by remember { mutableStateOf("Prof. R. Deshmukh") }

    var agreedToTerms by remember { mutableStateOf(true) }

    val registrationId = "SN-2026-BBL-8942"

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        if (isRegistrationComplete) {
            // ==========================================
            // SUCCESS SCREEN: "You're In! 🎉" PASS
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(VictoryGreen.copy(alpha = 0.2f))
                        .border(2.dp, VictoryGreen, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        tint = VictoryGreen,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "You're In! 🎉",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 32.sp,
                        color = Color.White
                    )
                )

                Text(
                    text = "Your tournament registration is officially confirmed.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Digital Pass Card
                GlassCard(
                    cornerRadius = 24.dp,
                    borderStrokeColor = NeonCyan.copy(alpha = 0.5f),
                    backgroundColor = Color(0xF00A1526),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "OFFICIAL REGISTRATION PASS",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = NeonCyan,
                                letterSpacing = 1.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = event.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // QR Code Icon
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Default.QrCode2,
                                contentDescription = "Pass QR",
                                tint = Color.Black,
                                modifier = Modifier.size(80.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "REGISTRATION ID",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                color = TextSecondary
                            )
                        )
                        Text(
                            text = registrationId,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Black,
                                color = NeonCyan,
                                letterSpacing = 1.5.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Metadata Rows
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Athlete",
                                    style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                )
                                Text(
                                    text = fullName,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Sport & Role",
                                    style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                )
                                Text(
                                    text = "${event.sport.displayName} • $positionRole",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                WhitePillButton(
                    text = "Download Confirmation & Pass",
                    onClick = onFinish
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextButton(onClick = onFinish) {
                    Text(
                        text = "Return to Dashboard",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = NeonCyan,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        } else {
            // ==========================================
            // WIZARD FORM: STEPS 1 TO 4
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Top Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularIconButton(
                        icon = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        onClick = {
                            if (currentStep > 1) currentStep-- else onBackClick()
                        },
                        size = 40.dp
                    )

                    Text(
                        text = "Step $currentStep of 4",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary
                        )
                    )

                    Spacer(modifier = Modifier.size(40.dp))
                }

                Spacer(modifier = Modifier.height(14.dp))

                // 4-Step Progress Indicator
                RegistrationProgressIndicator(currentStep = currentStep)

                Spacer(modifier = Modifier.height(20.dp))

                when (currentStep) {
                    1 -> {
                        // STEP 1: Personal Information
                        Text(
                            text = "Personal Information",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 22.sp,
                                color = TextPrimary
                            )
                        )
                        Text(
                            text = "Please verify your collegiate athletic identity.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        RegistrationInputField(label = "Full Name", value = fullName, onValueChange = { fullName = it })
                        RegistrationInputField(label = "College Name", value = collegeName, onValueChange = { collegeName = it })
                        RegistrationInputField(label = "Department / Major", value = department, onValueChange = { department = it })
                        RegistrationInputField(label = "Academic Year", value = year, onValueChange = { year = it })
                        RegistrationInputField(label = "Contact Mobile", value = contactNumber, onValueChange = { contactNumber = it })
                    }

                    2 -> {
                        // STEP 2: Sports Details
                        Text(
                            text = "Sports Details",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 22.sp,
                                color = TextPrimary
                            )
                        )
                        Text(
                            text = "Select your discipline and tactical position.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        RegistrationInputField(
                            label = "Sport Discipline",
                            value = "${selectedSport.iconEmoji} ${selectedSport.displayName}",
                            onValueChange = { }
                        )

                        Text(
                            text = "Participation Format",
                            style = MaterialTheme.typography.labelMedium.copy(color = TextSecondary)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(if (isTeamParticipation) Color.White else Color(0x3314223A))
                                    .border(1.dp, if (isTeamParticipation) Color.White else GlassBorderLight, RoundedCornerShape(14.dp))
                                    .clickable { isTeamParticipation = true }
                                    .padding(vertical = 12.dp)
                            ) {
                                Text(
                                    text = "Team Squad",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (isTeamParticipation) Color(0xFF070A10) else TextPrimary
                                    )
                                )
                            }

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(if (!isTeamParticipation) Color.White else Color(0x3314223A))
                                    .border(1.dp, if (!isTeamParticipation) Color.White else GlassBorderLight, RoundedCornerShape(14.dp))
                                    .clickable { isTeamParticipation = false }
                                    .padding(vertical = 12.dp)
                            ) {
                                Text(
                                    text = "Individual",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (!isTeamParticipation) Color(0xFF070A10) else TextPrimary
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        RegistrationInputField(label = "Primary Position / Role", value = positionRole, onValueChange = { positionRole = it })
                    }

                    3 -> {
                        // STEP 3: Team Details
                        Text(
                            text = "Team Details",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 22.sp,
                                color = TextPrimary
                            )
                        )
                        Text(
                            text = "Provide collegiate squad roster details.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        RegistrationInputField(label = "Official Team Name", value = teamName, onValueChange = { teamName = it })
                        RegistrationInputField(label = "Squad Size", value = squadSize, onValueChange = { squadSize = it })
                        RegistrationInputField(label = "Faculty / Coach Coordinator", value = coachName, onValueChange = { coachName = it })
                    }

                    4 -> {
                        // STEP 4: Review Registration
                        Text(
                            text = "Review Registration",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 22.sp,
                                color = TextPrimary
                            )
                        )
                        Text(
                            text = "Confirm all submission details prior to locking roster.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        GlassCard(
                            cornerRadius = 20.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                ReviewRow(label = "Event", value = event.title)
                                ReviewRow(label = "Student", value = fullName)
                                ReviewRow(label = "College", value = collegeName)
                                ReviewRow(label = "Department", value = department)
                                ReviewRow(label = "Sport & Role", value = "${selectedSport.displayName} ($positionRole)")
                                ReviewRow(label = "Team Squad", value = teamName)
                                ReviewRow(label = "Entry Fee", value = "Free (Varsity Pass)")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { agreedToTerms = !agreedToTerms }
                        ) {
                            Checkbox(
                                checked = agreedToTerms,
                                onCheckedChange = { agreedToTerms = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = NeonCyan,
                                    checkmarkColor = Color(0xFF070A10)
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "I verify that all submitted academic and sports credentials are authentic.",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Bottom Action Button: Next or Confirm Registration
                WhitePillButton(
                    text = if (currentStep < 4) "Proceed to Step ${currentStep + 1}" else "Confirm Registration",
                    onClick = {
                        if (currentStep < 4) {
                            currentStep++
                        } else {
                            isRegistrationComplete = true
                        }
                    },
                    enabled = if (currentStep == 4) agreedToTerms else true
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun RegistrationInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0x33101E35))
                .border(1.dp, GlassBorderLight, RoundedCornerShape(14.dp))
                .padding(horizontal = 14.dp, vertical = 2.dp)
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ReviewRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
