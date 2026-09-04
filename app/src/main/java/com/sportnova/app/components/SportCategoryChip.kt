package com.sportnova.app.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sportnova.app.model.SportType
import com.sportnova.app.theme.*

/**
 * Sport Category Pill Chip
 * Matches exactly: Active is Crisp Solid White with Black Text;
 * Inactive is Frosted Dark Glass with Soft White/Slate Text.
 */
@Composable
fun SportCategoryChip(
    sport: SportType,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) PillActiveBackground else PillInactiveBackground,
        label = "chipBg"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) PillActiveText else PillInactiveText,
        label = "chipText"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else GlassBorderLight,
        label = "chipBorder"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(50.dp))
            .clickable { onSelect() }
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        Text(
            text = sport.iconEmoji,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = sport.displayName,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 13.sp,
                color = textColor
            )
        )
    }
}
