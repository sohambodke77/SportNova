package com.sportnova.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.components.NotificationCard
import com.sportnova.app.model.AppNotification
import com.sportnova.app.model.MockSportsData
import com.sportnova.app.model.NotificationCategory
import com.sportnova.app.theme.*

/**
 * Screen 9: Notifications Center
 * Categorized alerts for matches, approvals, deadlines, and achievements.
 */
@Composable
fun NotificationsScreen(
    onNotificationClick: (AppNotification) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(NotificationCategory.ALL) }
    var notificationsList by remember { mutableStateOf(MockSportsData.notifications) }

    val filteredNotifications = remember(selectedCategory, notificationsList) {
        if (selectedCategory == NotificationCategory.ALL) {
            notificationsList
        } else {
            notificationsList.filter { it.category == selectedCategory }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(bottom = 90.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Header Row: Title + "Mark all as read"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 28.sp,
                    color = TextPrimary
                )
            )

            Text(
                text = "Mark all as read",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = NeonCyan
                ),
                modifier = Modifier.clickable {
                    notificationsList = notificationsList.map { it.copy(isRead = true) }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category Filter Chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(NotificationCategory.values()) { category ->
                val isSelected = selectedCategory == category
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(if (isSelected) Color.White else Color(0x33121F35))
                        .clickable { selectedCategory = category }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = category.label,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 12.sp,
                            color = if (isSelected) Color(0xFF070A10) else TextSecondary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications List
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (filteredNotifications.isEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                    ) {
                        Text(
                            text = "No notifications in this category.",
                            style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                        )
                    }
                }
            } else {
                items(filteredNotifications) { item ->
                    NotificationCard(
                        notification = item,
                        onClick = {
                            // Mark as read on click
                            notificationsList = notificationsList.map {
                                if (it.id == item.id) it.copy(isRead = true) else it
                            }
                            onNotificationClick(item)
                        }
                    )
                }
            }
        }
    }
}
