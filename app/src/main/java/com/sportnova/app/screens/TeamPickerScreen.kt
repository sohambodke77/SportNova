package com.sportnova.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.AthleteSilhouetteVisual
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.GlassPillButton
import com.sportnova.app.components.WhitePillButton
import com.sportnova.app.model.CollegeTeam
import com.sportnova.app.model.MockSportsData
import com.sportnova.app.model.SportType
import com.sportnova.app.theme.*

/**
 * Screen 3: "Choose Your Teams"
 * Exact match for the user's reference mockup right screen:
 * - Top Bar: Circular Back + Circular Profile
 * - Hero Visual: Back-lit athlete silhouette holding basketball with studio blue rim-light
 * - Bold Headline: "Choose Your Teams"
 * - Subtitle: "Pick Teams Earn Custom Rewards" + pagination dots
 * - Rounded Frosted Glass Container with team list:
 *   Team emblem, Name, Category, "Learn More" / "Select" pill
 * - Large solid white pill button: "Continue"
 */
@Composable
fun TeamPickerScreen(
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    onTeamSelect: (CollegeTeam) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTeamId by remember { mutableStateOf("TM-01") } // MIT AOE Titans default selected
    val teams = MockSportsData.collegeTeams

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // ==========================================
            // TOP BAR: Circular Back + Circular Profile
            // ==========================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularIconButton(
                    icon = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    onClick = onBackClick,
                    size = 42.dp
                )

                CircularIconButton(
                    icon = Icons.Default.Person,
                    contentDescription = "Profile",
                    onClick = onBackClick,
                    size = 42.dp
                )
            }

            // ==========================================
            // HERO SECTION: Athletic Player Silhouette + Headline + Pagination
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Dramatic silhouette holding a ball with atmospheric blue studio glow
                AthleteSilhouetteVisual(
                    modifier = Modifier
                        .size(160.dp)
                        .padding(top = 4.dp),
                    sportType = SportType.BASKETBALL,
                    accentGlowColor = ElectricBlue
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Bold Headline: "Choose Your Teams"
                Text(
                    text = "Choose Your Teams",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 26.sp,
                        color = TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Subtitle: "Pick Teams Earn Custom Rewards"
                Text(
                    text = "Pick Teams Earn Custom Rewards",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Pagination dots (● ○ ○)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(Color(0x55FFFFFF))
                    )
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(Color(0x55FFFFFF))
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ==========================================
            // BOTTOM ROUNDED CONTAINER: Teams List & "Continue"
            // Matches the curved frosted glass sheet in Screen 3
            // ==========================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(Color(0xEE0B1526))
                    .border(1.dp, GlassBorderLight, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header inside sheet
                    Text(
                        text = "College Teams (Extra Points!)",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = TextPrimary
                        ),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Teams List
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(teams) { team ->
                            val isSelected = team.id == selectedTeamId

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(if (isSelected) Color(0x332563EB) else Color(0x26121F35))
                                    .border(
                                        1.dp,
                                        if (isSelected) NeonCyan else GlassBorderLight,
                                        RoundedCornerShape(18.dp)
                                    )
                                    .clickable {
                                        selectedTeamId = team.id
                                        onTeamSelect(team)
                                    }
                                    .padding(horizontal = 14.dp, vertical = 12.dp)
                            ) {
                                // Left: Team Crest & Name & League
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Team Crest Circle
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF16253C))
                                            .border(1.dp, GlassBorderBlue, CircleShape)
                                    ) {
                                        Text(
                                            text = team.teamName.take(2).uppercase(),
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = NeonCyan
                                            )
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Text(
                                            text = team.teamName,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                color = TextPrimary
                                            )
                                        )
                                        Text(
                                            text = "${team.category} • ${team.winRate}",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                fontSize = 11.sp
                                            )
                                        )
                                    }
                                }

                                // Right: "Learn More" / "Select" pill button
                                GlassPillButton(
                                    text = if (isSelected) "Selected ✓" else "Learn More",
                                    isSelected = isSelected,
                                    onClick = {
                                        selectedTeamId = team.id
                                        onTeamSelect(team)
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Bottom CTA: Large solid white pill button "Continue"
                    WhitePillButton(
                        text = "Continue",
                        onClick = onContinueClick
                    )
                }
            }
        }
    }
}
