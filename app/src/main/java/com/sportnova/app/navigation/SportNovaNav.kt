package com.sportnova.app.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.dialogs.DigitalCertificateDialog
import com.sportnova.app.dialogs.LiveScoreboardDialog
import com.sportnova.app.model.*
import com.sportnova.app.screens.*
import com.sportnova.app.theme.*

// ==========================================
// NAVIGATION DESTINATIONS
// ==========================================

enum class BottomTab(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home),
    EVENTS("Events", Icons.Filled.EmojiEvents, Icons.Outlined.EmojiEvents),
    EXPLORE("Explore", Icons.Filled.Explore, Icons.Outlined.Explore),
    NOTIFICATIONS("Alerts", Icons.Filled.Notifications, Icons.Outlined.Notifications),
    PROFILE("Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

enum class SubScreen {
    NONE,
    AI_HUB,
    TEAM_PICKER,
    INTRA_COLLEGE,
    INTER_COLLEGE,
    EVENT_DETAILS,
    EVENT_REGISTRATION,
    CERTIFICATES
}

/**
 * Main Application Navigation Container
 * Orchestrates all 10 screens, the 3 reference image views, and dialogs.
 */
@Composable
fun SportNovaApp(
    modifier: Modifier = Modifier
) {
    var currentTab by remember { mutableStateOf(BottomTab.HOME) }
    var currentSubScreen by remember { mutableStateOf(SubScreen.NONE) }

    // Active Selection States
    var selectedEvent by remember { mutableStateOf(MockSportsData.allEvents.first()) }
    var activeLiveMatch by remember { mutableStateOf<LiveMatch?>(null) }
    var activeCertificate by remember { mutableStateOf<DigitalCertificate?>(null) }

    // Intercept Back Press for SubScreens
    BackHandler(enabled = currentSubScreen != SubScreen.NONE) {
        currentSubScreen = SubScreen.NONE
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        // ==========================================
        // SCREEN CONTENT ROUTER
        // ==========================================
        when (currentSubScreen) {
            SubScreen.AI_HUB -> {
                AiAssistantHubScreen(
                    onBackClick = { currentSubScreen = SubScreen.NONE },
                    onFeatureClick = { }
                )
            }
            SubScreen.TEAM_PICKER -> {
                TeamPickerScreen(
                    onBackClick = { currentSubScreen = SubScreen.NONE },
                    onContinueClick = { currentSubScreen = SubScreen.NONE },
                    onTeamSelect = { }
                )
            }
            SubScreen.INTRA_COLLEGE -> {
                IntraCollegeScreen(
                    onEventClick = {
                        selectedEvent = it
                        currentSubScreen = SubScreen.EVENT_DETAILS
                    },
                    onRegisterClick = {
                        selectedEvent = it
                        currentSubScreen = SubScreen.EVENT_REGISTRATION
                    }
                )
            }
            SubScreen.INTER_COLLEGE -> {
                InterCollegeScreen(
                    onEventClick = {
                        selectedEvent = it
                        currentSubScreen = SubScreen.EVENT_DETAILS
                    },
                    onRegisterClick = {
                        selectedEvent = it
                        currentSubScreen = SubScreen.EVENT_REGISTRATION
                    }
                )
            }
            SubScreen.EVENT_DETAILS -> {
                EventDetailsScreen(
                    event = selectedEvent,
                    onBackClick = { currentSubScreen = SubScreen.NONE },
                    onRegisterClick = {
                        selectedEvent = it
                        currentSubScreen = SubScreen.EVENT_REGISTRATION
                    }
                )
            }
            SubScreen.EVENT_REGISTRATION -> {
                EventRegistrationScreen(
                    event = selectedEvent,
                    onBackClick = { currentSubScreen = SubScreen.EVENT_DETAILS },
                    onFinish = { currentSubScreen = SubScreen.NONE }
                )
            }
            SubScreen.CERTIFICATES -> {
                AchievementsCertificatesScreen(
                    onBackClick = { currentSubScreen = SubScreen.NONE },
                    onCertificateClick = { activeCertificate = it }
                )
            }
            SubScreen.NONE -> {
                // Tab Screens
                when (currentTab) {
                    BottomTab.HOME -> {
                        HomeScreen(
                            athlete = MockSportsData.currentAthlete,
                            liveMatch = MockSportsData.liveBasketballMatch,
                            upcomingEvents = MockSportsData.allEvents,
                            onEventClick = {
                                selectedEvent = it
                                currentSubScreen = SubScreen.EVENT_DETAILS
                            },
                            onRegisterClick = {
                                selectedEvent = it
                                currentSubScreen = SubScreen.EVENT_REGISTRATION
                            },
                            onViewLiveDetails = { activeLiveMatch = it },
                            onNavigateToIntra = { currentSubScreen = SubScreen.INTRA_COLLEGE },
                            onNavigateToInter = { currentSubScreen = SubScreen.INTER_COLLEGE },
                            onNavigateToProfile = { currentTab = BottomTab.PROFILE },
                            onNavigateToNotifications = { currentTab = BottomTab.NOTIFICATIONS },
                            onNavigateToLeaderboard = { currentSubScreen = SubScreen.INTER_COLLEGE }
                        )
                    }
                    BottomTab.EVENTS -> {
                        DiscoverEventsScreen(
                            onEventClick = {
                                selectedEvent = it
                                currentSubScreen = SubScreen.EVENT_DETAILS
                            },
                            onRegisterClick = {
                                selectedEvent = it
                                currentSubScreen = SubScreen.EVENT_REGISTRATION
                            }
                        )
                    }
                    BottomTab.EXPLORE -> {
                        ExploreScreen(
                            onEventClick = {
                                selectedEvent = it
                                currentSubScreen = SubScreen.EVENT_DETAILS
                            },
                            onRegisterClick = {
                                selectedEvent = it
                                currentSubScreen = SubScreen.EVENT_REGISTRATION
                            },
                            onLaunchAiHub = { currentSubScreen = SubScreen.AI_HUB },
                            onLaunchTeamPicker = { currentSubScreen = SubScreen.TEAM_PICKER }
                        )
                    }
                    BottomTab.NOTIFICATIONS -> {
                        NotificationsScreen(
                            onNotificationClick = { }
                        )
                    }
                    BottomTab.PROFILE -> {
                        ProfileScreen(
                            athlete = MockSportsData.currentAthlete,
                            onAchievementClick = { },
                            onEventClick = {
                                selectedEvent = it
                                currentSubScreen = SubScreen.EVENT_DETAILS
                            },
                            onNavigateToCertificates = { currentSubScreen = SubScreen.CERTIFICATES }
                        )
                    }
                }
            }
        }

        // ==========================================
        // FLOATING GLASS BOTTOM NAVIGATION BAR
        // Visible on main tab views with animated glowing indicator
        // ==========================================
        AnimatedVisibility(
            visible = currentSubScreen == SubScreen.NONE,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xF20B1424))
                    .border(1.dp, GlassBorderLight, RoundedCornerShape(50.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomTab.values().forEach { tab ->
                        val isSelected = currentTab == tab
                        val iconColor by animateColorAsState(
                            targetValue = if (isSelected) Color.White else TextSecondary,
                            label = "tabIconColor"
                        )
                        val bgColor by animateColorAsState(
                            targetValue = if (isSelected) Color(0x332563EB) else Color.Transparent,
                            label = "tabBgColor"
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(bgColor)
                                .clickable { currentTab = tab }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = tab.label,
                                tint = iconColor,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = tab.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) NeonCyan else TextSecondary
                                )
                            )
                        }
                    }
                }
            }
        }

        // ==========================================
        // MODAL DIALOGS
        // ==========================================
        activeLiveMatch?.let { match ->
            LiveScoreboardDialog(
                match = match,
                onDismiss = { activeLiveMatch = null }
            )
        }

        activeCertificate?.let { cert ->
            DigitalCertificateDialog(
                certificate = cert,
                onDismiss = { activeCertificate = null }
            )
        }
    }
}
