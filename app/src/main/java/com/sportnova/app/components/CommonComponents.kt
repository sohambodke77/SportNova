package com.sportnova.app.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sportnova.app.theme.*

// ==========================================
// SPORTNOVA EXACT REUSABLE UI COMPONENTS
// ==========================================

/**
 * Premium Frosted Glassmorphism Card Container
 * Used for all major cards matching the reference image
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    borderStrokeColor: Color = GlassBorderLight,
    backgroundColor: Color = SurfaceCardGlass,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    val clickableModifier = if (onClick != null) {
        Modifier.clickable { onClick() }
    } else Modifier

    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor, shape)
            .border(1.dp, borderStrokeColor, shape)
            .then(clickableModifier)
    ) {
        content()
    }
}

/**
 * Capsule Badge with glowing outline
 * Examples: "YOU'RE ENTERED!", "✨ AI Insights", "LIVE"
 */
@Composable
fun SportNovaBadge(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0x33000000),
    borderColor: Color = GlassBorderBlue,
    textColor: Color = TextPrimary,
    leadingIcon: ImageVector? = null,
    isPulsing: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by if (isPulsing) {
        infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(800, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "alpha"
        )
    } else {
        remember { mutableFloatStateOf(1.0f) }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(backgroundColor.copy(alpha = backgroundColor.alpha * alpha))
            .border(1.dp, borderColor.copy(alpha = borderColor.alpha * alpha), RoundedCornerShape(50.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .size(12.dp)
                    .padding(end = 4.dp)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                letterSpacing = 0.6.sp
            ),
            color = textColor
        )
    }
}

/**
 * Crisp Solid White Rounded Pill Button
 * Exact match for "Continue" button and primary actions
 */
@Composable
fun WhitePillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PillActiveBackground,
            contentColor = PillActiveText,
            disabledContainerColor = Color(0x33FFFFFF),
            disabledContentColor = Color(0x66FFFFFF)
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = PillActiveText
            )
        )
    }
}

/**
 * Frosted Glass Pill Button
 * Exact match for "Learn More", "Select", "View Details"
 */
@Composable
fun GlassPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    val bgColor = if (isSelected) ElectricBlue else Color(0x40162338)
    val textColor = TextPrimary

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(bgColor)
            .border(1.dp, GlassBorderLight, RoundedCornerShape(50.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            ),
            color = textColor
        )
    }
}

/**
 * Frosted Glass Metric Bubble
 * Exact match for Score / Time stacked indicators in Screen 1
 */
@Composable
fun GlassMetricBubble(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(58.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0x550B1322))
            .border(1.dp, GlassBorderLight, RoundedCornerShape(16.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    color = TextSecondary
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = TextPrimary
                )
            )
        }
    }
}

/**
 * Frosted Circular Icon Button
 * Used in TopBar for notifications, back arrows, mic, profile
 */
@Composable
fun CircularIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 44.dp,
    tint: Color = TextPrimary,
    badgeCount: Int = 0
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Color(0x40121C2F))
            .border(1.dp, GlassBorderLight, CircleShape)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size * 0.48f)
        )

        if (badgeCount > 0) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 2.dp, y = (-2).dp)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(LivePulsingRed)
            ) {
                Text(
                    text = if (badgeCount > 9) "9+" else badgeCount.toString(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    }
}

/**
 * Standard Section Header with optional "View all" action
 */
@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextPrimary
            )
        )

        if (actionText != null && onActionClick != null) {
            Text(
                text = actionText,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 13.sp,
                    color = TextSecondary
                ),
                modifier = Modifier.clickable { onActionClick() }
            )
        }
    }
}

/**
 * Glowing Concentric Circular AI Radar Visual
 * Exact match for Center Screen 2 "SportNova.AI Assistant Hub"
 */
@Composable
fun AiConcentricRadar(
    modifier: Modifier = Modifier,
    size: Dp = 180.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "radarPulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.94f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "radarPulseScale"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        // Outer concentric ring 3 (faintest)
        Box(
            modifier = Modifier
                .size(size * pulseScale)
                .clip(CircleShape)
                .border(1.dp, Color(0x1F3B82F6), CircleShape)
                .background(Color(0x081D4ED8), CircleShape)
        )

        // Concentric ring 2
        Box(
            modifier = Modifier
                .size(size * 0.78f)
                .clip(CircleShape)
                .border(1.5.dp, Color(0x442563EB), CircleShape)
                .background(Color(0x1A1E40AF), CircleShape)
        )

        // Core Glowing Orb
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size * 0.52f)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        listOf(
                            Color(0xFF38BDF8),
                            Color(0xFF2563EB),
                            Color(0xFF1E3A8A)
                        )
                    ),
                    CircleShape
                )
                .border(2.dp, Color(0x8093C5FD), CircleShape)
        ) {
            // Soundwave / AI Spark Icon
            Icon(
                imageVector = Icons.Default.GraphicEq,
                contentDescription = "AI Soundwave",
                tint = Color.White,
                modifier = Modifier.size(34.dp)
            )
        }
    }
}

/**
 * Dynamic Procedural Athletic Player Silhouette Visual
 * Matches the atmospheric rim-lit basketball/soccer athlete visuals from the user's mockup
 */
@Composable
fun AthleteSilhouetteVisual(
    modifier: Modifier = Modifier,
    sportType: SportType = SportType.BASKETBALL,
    accentGlowColor: Color = ElectricBlue
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Background Atmospheric Blue Glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    accentGlowColor.copy(alpha = 0.45f),
                    accentGlowColor.copy(alpha = 0.15f),
                    Color.Transparent
                ),
                center = Offset(w * 0.5f, h * 0.45f),
                radius = w * 0.7f
            )
        )

        // Player Torso & Head Silhouette
        val headRadius = w * 0.14f
        val headCenter = Offset(w * 0.48f, h * 0.28f)

        // Head
        drawCircle(
            color = Color(0xFF0C1626),
            center = headCenter,
            radius = headRadius
        )
        // Head Rim Light
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(NeonCyan.copy(alpha = 0.8f), Color.Transparent),
                start = Offset(headCenter.x - headRadius, headCenter.y - headRadius),
                end = Offset(headCenter.x + headRadius, headCenter.y + headRadius)
            ),
            center = headCenter,
            radius = headRadius
        )

        // Shoulders & Athletic Torso
        val torsoPath = Path().apply {
            moveTo(w * 0.22f, h * 0.95f)
            lineTo(w * 0.32f, h * 0.48f)
            cubicTo(w * 0.34f, h * 0.40f, w * 0.62f, h * 0.40f, w * 0.64f, h * 0.48f)
            lineTo(w * 0.74f, h * 0.95f)
            close()
        }

        drawPath(
            path = torsoPath,
            color = Color(0xFF08101E)
        )

        // Rim lighting on shoulders
        drawPath(
            path = torsoPath,
            brush = Brush.verticalGradient(
                colors = listOf(NeonCyan.copy(alpha = 0.6f), Color.Transparent),
                startY = h * 0.38f,
                endY = h * 0.85f
            )
        )

        // Basketball / Ball in Hands
        val ballCenter = Offset(w * 0.76f, h * 0.62f)
        val ballRadius = w * 0.16f

        drawCircle(
            color = Color(0xFFE65100),
            center = ballCenter,
            radius = ballRadius
        )
        // Ball Rim Glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFFF9800),
                    Color(0xFFE65100),
                    Color(0xFF3E2723)
                ),
                center = ballCenter,
                radius = ballRadius
            ),
            center = ballCenter,
            radius = ballRadius
        )
    }
}
