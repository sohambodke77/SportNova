package com.sportnova.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.*
import com.sportnova.app.model.*
import com.sportnova.app.theme.*

/**
 * Screen 1: Home Dashboard
 * Exact match for the user's reference mockup left screen:
 * - TopBar: Avatar + "Good Morning!" + Name + Frosted Bell
 * - Display Headline: "Come hang out with \nSportNova Arena"
 * - Hero Card: Frosted card with "YOU'RE ENTERED!" + Subtitle + Athlete Silhouette
 * - Category Chips: White active pill ("Basketball") + Frosted inactive pills
 * - "Today's Match Intelligence" glass card with AI insights & metric bubbles
 * - Leaderboard strip (#1 Michael Brown 8825200)
 * - Plus College Ecosystem Quick Access & Upcoming Events
 */
@Composable
fun HomeScreen(
    athlete: AthleteProfile,
    liveMatch: LiveMatch,
    upcomingEvents: List<SportEvent>,
    onEventClick: (SportEvent) -> Unit,
    onRegisterClick: (SportEvent) -> Unit,
    onViewLiveDetails: (LiveMatch) -> Unit,
    onNavigateToIntra: () -> Unit,
    onNavigateToInter: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSport by remember { mutableStateOf(SportType.BASKETBALL) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 90.dp) // Space for bottom nav bar
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // ==========================================
        // TOP APP BAR: Avatar + Greeting + Bell Icon
        // ==========================================
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Circular Avatar + Greeting + Name
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar with thin glowing border ring
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF132238))
                        .border(1.5.dp, NeonCyan, CircleShape)
                        .clickable { onNavigateToProfile() }
                ) {
                    Text(
                        text = athlete.studentName.take(2).uppercase(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Good Morning!",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    )
                    Text(
                        text = athlete.studentName,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextPrimary
                        )
                    )
                }
            }

            // Right: Circular frosted bell button
            CircularIconButton(
                icon = Icons.Default.Notifications,
                contentDescription = "Notifications",
                onClick = onNavigateToNotifications,
                badgeCount = 2,
                size = 42.dp
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // ==========================================
        // HERO HEADLINE (Exact match to reference)
        // "Come hang out with SportNova Arena"
        // ==========================================
        Text(
            text = "Come hang out with\nSportNova Arena",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Black,
                fontSize = 28.sp,
                lineHeight = 34.sp,
                color = TextPrimary
            ),
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // ==========================================
        // HERO CARD: "YOU'RE ENTERED!" + Athlete Visual
        // ==========================================
        GlassCard(
            cornerRadius = 24.dp,
            borderStrokeColor = GlassBorderBlue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable {
                    upcomingEvents.firstOrNull()?.let { onEventClick(it) }
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                // Background gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xEE0B1930),
                                    Color(0xD9071020)
                                )
                            )
                        )
                )

                // Athlete silhouette illustration on right
                AthleteSilhouetteVisual(
                    modifier = Modifier
                        .size(170.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = 10.dp),
                    sportType = SportType.BASKETBALL,
                    accentGlowColor = ElectricBlue
                )

                // Text Content on left
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 18.dp, top = 18.dp, bottom = 18.dp, end = 120.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // "YOU'RE ENTERED!" Pill Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0x4D000000))
                            .border(1.dp, Color(0x6638BDF8), RoundedCornerShape(50.dp))
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "YOU'RE ENTERED!",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 10.sp,
                                letterSpacing = 0.8.sp,
                                color = Color.White
                            )
                        )
                    }

                    Column {
                        Text(
                            text = "Good Luck! You're Now Entered For",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        )
                        Text(
                            text = "Inter-College Sports Fest 2026",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==========================================
        // SPORT CATEGORY FILTER CHIPS
        // Active: White pill / Inactive: Frosted Dark pill
        // ==========================================
        val sportFilters = listOf(
            SportType.BASKETBALL,
            SportType.SOCCER,
            SportType.TENNIS,
            SportType.VOLLEYBALL,
            SportType.CRICKET,
            SportType.BADMINTON
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(sportFilters) { sport ->
                SportCategoryChip(
                    sport = sport,
                    isSelected = selectedSport == sport,
                    onSelect = { selectedSport = sport }
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // ==========================================
        // TODAY'S MATCH INTELLIGENCE (Exact match to reference)
        // With AI Insights & Metric Bubbles
        // ==========================================
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            LiveScoreCard(
                match = liveMatch,
                onViewLiveDetails = onViewLiveDetails
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==========================================
        // LEADERBOARD SECTION STRIP (Exact match to reference)
        // "Leaderboard" (left) | "View all" (right)
        // ==========================================
        SectionHeader(
            title = "Leaderboard",
            actionText = "View all",
            onActionClick = onNavigateToLeaderboard
        )

        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            LeaderboardItem(
                entry = MockSportsData.leaderboards[0], // Michael Brown #1
                onItemClick = { onNavigateToLeaderboard() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ==========================================
        // QUICK ACCESS CARDS (Intra, Inter, Profile)
        // ==========================================
        SectionHeader(title = "Campus Sports Hub")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Intra College
            GlassCard(
                cornerRadius = 18.dp,
                borderStrokeColor = GlassBorderBlue,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateToIntra() }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Intra College",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Dept Shield",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    )
                }
            }

            // Inter College
            GlassCard(
                cornerRadius = 18.dp,
                borderStrokeColor = GlassBorderBlue,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateToInter() }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Icon(
                        imageVector = Icons.Default.Public,
                        contentDescription = null,
                        tint = ElectricBlue,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Inter College",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Varsity Fest",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    )
                }
            }

            // My Profile
            GlassCard(
                cornerRadius = 18.dp,
                borderStrokeColor = GlassBorderLight,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateToProfile() }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = GoldMedal,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "My Profile",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Rank #3",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ==========================================
        // UPCOMING EVENTS HORIZONTAL CAROUSEL
        // ==========================================
        SectionHeader(
            title = "Upcoming Events",
            actionText = "Discover",
            onActionClick = { upcomingEvents.firstOrNull()?.let { onEventClick(it) } }
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(upcomingEvents) { event ->
                Box(modifier = Modifier.width(300.dp)) {
                    EventCard(
                        event = event,
                        onViewDetails = onEventClick,
                        onRegisterClick = onRegisterClick
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
