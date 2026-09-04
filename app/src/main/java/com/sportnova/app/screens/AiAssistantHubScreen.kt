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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.AiConcentricRadar
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.GlassCard
import com.sportnova.app.theme.*

/**
 * Screen 2: Smart AI Sports Assistant Hub
 * Exact match for the user's reference mockup center screen:
 * - Top Bar: Circular icon + "SportNova.AI" capsule badge
 * - Large glowing concentric circular AI radar badge
 * - Headline: "Your Smart AI Sports \nAssistant Hub"
 * - 2x2 Grid of frosted cards: Match Insights, Team Analysis, Injury Updates, Player Stats
 * - Floating bottom voice & query capsule bar with Send & Mic buttons
 */
@Composable
fun AiAssistantHubScreen(
    onBackClick: () -> Unit,
    onFeatureClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var queryText by remember { mutableStateOf("") }
    var activeInsight by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        // Atmospheric deep blue radial background glow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0x331E40AF),
                            Color(0x1A1E3A8A),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 110.dp), // Space for floating bottom bar
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // TOP BAR: Left circular button + Center Pill Badge "SportNova.AI"
            // ==========================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularIconButton(
                    icon = Icons.Default.GraphicEq,
                    contentDescription = "AI Mode",
                    onClick = onBackClick,
                    size = 42.dp
                )

                Spacer(modifier = Modifier.weight(1f))

                // Center Pill "SportNova.AI"
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0x55111B2E))
                        .border(1.dp, GlassBorderLight, RoundedCornerShape(50.dp))
                        .padding(horizontal = 16.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = "SportNova.AI",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            fontSize = 12.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Spacer(modifier = Modifier.size(42.dp)) // Visual balance
            }

            Spacer(modifier = Modifier.height(26.dp))

            // ==========================================
            // CENTER GLOWING CONCENTRIC RADAR VISUAL
            // ==========================================
            AiConcentricRadar(
                size = 175.dp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ==========================================
            // HEADLINE: "Your Smart AI Sports \nAssistant Hub"
            // ==========================================
            Text(
                text = "Your Smart AI Sports\nAssistant Hub",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 25.sp,
                    lineHeight = 31.sp,
                    textAlign = TextAlign.Center,
                    color = TextPrimary
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ==========================================
            // 2x2 GRID OF GLASS FEATURE CARDS
            // Match Insights, Team Analysis, Injury Updates, Player Stats
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Row 1: Match Insights & Team Analysis
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    AiFeatureCard(
                        title = "Match Insights",
                        description = "Get updates and analyses on any match",
                        icon = Icons.Default.Analytics,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            activeInsight = "MIT AOE is shooting 48% from beyond the arc in the 4th quarter. Pressure defense suggested on wing."
                            onFeatureClick("Match Insights")
                        }
                    )

                    AiFeatureCard(
                        title = "Team Analysis",
                        description = "Compare teams' stats and performance",
                        icon = Icons.Default.Groups,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            activeInsight = "COEP Warriors fast-break transition pace is 8.4 seconds per posession. Fast defensive retreats required."
                            onFeatureClick("Team Analysis")
                        }
                    )
                }

                // Row 2: Injury & Squad Updates & Player Stats
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    AiFeatureCard(
                        title = "Injury Updates",
                        description = "Get the latest squad news and updates",
                        icon = Icons.Default.Healing,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            activeInsight = "Starting point guard has full medical clearance. All 15 varsity squad members active for tournament."
                            onFeatureClick("Injury Updates")
                        }
                    )

                    AiFeatureCard(
                        title = "Player Stats",
                        description = "Check player performance and records",
                        icon = Icons.Default.PersonSearch,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            activeInsight = "Sanket Bodke: 24.5 PPG | 8.2 APG | Rank #3 Statewide. On track for Collegiate All-American selection."
                            onFeatureClick("Player Stats")
                        }
                    )
                }
            }

            // Optional Active AI Insight Balloon
            if (activeInsight != null) {
                Spacer(modifier = Modifier.height(18.dp))
                GlassCard(
                    cornerRadius = 18.dp,
                    borderStrokeColor = NeonCyan.copy(alpha = 0.5f),
                    backgroundColor = Color(0xDD0D1D34),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = NeonCyan,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = activeInsight!!,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextPrimary,
                                fontSize = 12.sp,
                                lineHeight = 17.sp
                            )
                        )
                    }
                }
            }
        }

        // ==========================================
        // BOTTOM FLOATING VOICE & QUERY BAR (Exact match to reference)
        // Capsule bar with "Describe everything here..." + Send + White Mic
        // ==========================================
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xE60A1322))
                .border(1.dp, GlassBorderLight, RoundedCornerShape(50.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Input Placeholder / Text Field
                Text(
                    text = if (queryText.isEmpty()) "Describe everything here..." else queryText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (queryText.isEmpty()) TextMuted else TextPrimary,
                        fontSize = 13.sp
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .clickable {
                            queryText = "Analyze MIT AOE vs COEP final tactics..."
                            activeInsight = "AI Match Analysis: COEP runs high pick-and-rolls. Recommend switching to 2-3 zone defense."
                        }
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Send Arrow Button
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0x331E293B))
                            .clickable {
                                if (queryText.isNotEmpty()) {
                                    activeInsight = "Query processed for: '$queryText'. Optimal lineup calculated."
                                }
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // Crisp White Circular Microphone Button
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable {
                                queryText = "Voice query: Who leads in 3-pointers?"
                                activeInsight = "Voice recognized! Sanket Bodke leads the tournament with 28 made 3-pointers."
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Voice Input",
                            tint = Color(0xFF070A10),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * 2x2 Frosted AI Feature Card
 */
@Composable
private fun AiFeatureCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(
        cornerRadius = 20.dp,
        borderStrokeColor = GlassBorderLight,
        backgroundColor = Color(0x660E1A2C),
        modifier = modifier
            .height(130.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon in blue circular tint
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0x332563EB))
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = NeonCyan,
                    modifier = Modifier.size(18.dp)
                )
            }

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 10.sp,
                        lineHeight = 14.sp
                    ),
                    maxLines = 2
                )
            }
        }
    }
}
