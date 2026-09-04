package com.sportnova.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.EventCard
import com.sportnova.app.components.SportCategoryChip
import com.sportnova.app.model.EventStatus
import com.sportnova.app.model.MockSportsData
import com.sportnova.app.model.SportEvent
import com.sportnova.app.model.SportType
import com.sportnova.app.theme.*

/**
 * Screen 2: Events / Discover Screen
 * Search bar, Sports category chips, status badges, and comprehensive event list.
 */
@Composable
fun DiscoverEventsScreen(
    onEventClick: (SportEvent) -> Unit,
    onRegisterClick: (SportEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedSport by remember { mutableStateOf(SportType.ALL) }
    var selectedStatus by remember { mutableStateOf<EventStatus?>(null) }

    val allEvents = MockSportsData.allEvents

    val filteredEvents = remember(searchQuery, selectedSport, selectedStatus) {
        allEvents.filter { event ->
            val matchesSport = selectedSport == SportType.ALL || event.sport == selectedSport
            val matchesStatus = selectedStatus == null || event.status == selectedStatus
            val matchesSearch = searchQuery.isEmpty() ||
                    event.title.contains(searchQuery, ignoreCase = true) ||
                    event.location.contains(searchQuery, ignoreCase = true) ||
                    event.organizerCollege.contains(searchQuery, ignoreCase = true)
            matchesSport && matchesStatus && matchesSearch
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(bottom = 90.dp) // space for bottom nav
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Title and Subtitle
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Discover Events",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 28.sp,
                    color = TextPrimary
                )
            )
            Text(
                text = "Find competitions. Compete. Achieve.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar + Filter Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Search Input Capsule
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0x33121F35))
                    .border(1.dp, GlassBorderLight, RoundedCornerShape(50.dp))
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = {
                            Text(
                                text = "Search sports, colleges, venues...",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextMuted,
                                    fontSize = 13.sp
                                )
                            )
                        },
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

            // Filter Icon Button
            CircularIconButton(
                icon = Icons.Default.FilterList,
                contentDescription = "Filter",
                onClick = {
                    // Toggle status filter cycle
                    selectedStatus = when (selectedStatus) {
                        null -> EventStatus.REGISTRATION_OPEN
                        EventStatus.REGISTRATION_OPEN -> EventStatus.CLOSING_SOON
                        EventStatus.CLOSING_SOON -> EventStatus.LIVE
                        else -> null
                    }
                },
                size = 46.dp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Sports Category Horizontal Chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(SportType.values()) { sport ->
                SportCategoryChip(
                    sport = sport,
                    isSelected = selectedSport == sport,
                    onSelect = { selectedSport = sport }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Events List
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (filteredEvents.isEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                    ) {
                        Text(
                            text = "No events found matching criteria.",
                            style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                        )
                    }
                }
            } else {
                items(filteredEvents) { event ->
                    EventCard(
                        event = event,
                        onViewDetails = onEventClick,
                        onRegisterClick = onRegisterClick
                    )
                }
            }
        }
    }
}
