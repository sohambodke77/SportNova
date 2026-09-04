package com.sportnova.app.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// ==========================================
// SPORTNOVA EXACT DESIGN SYSTEM COLOR PALETTE
// ==========================================

// Base Backgrounds (Deep Midnight Sports Black & Vignette)
val BackgroundDark = Color(0xFF06090F)
val BackgroundPitch = Color(0xFF04060A)
val SurfaceDark = Color(0xFF0B121E)
val SurfaceCard = Color(0xFF0F1A2C)
val SurfaceCardElevated = Color(0xFF14223A)
val SurfaceCardGlass = Color(0xCC0D1728)

// Glass Borders & Outlines
val GlassBorderLight = Color(0x26FFFFFF)
val GlassBorderBlue = Color(0x333B82F6)
val GlassBorderActive = Color(0x6600E5FF)

// Highlights & Accents
val ElectricBlue = Color(0xFF2B7FFF)
val NeonCyan = Color(0xFF00E5FF)
val DeepCobalt = Color(0xFF1E40AF)
val RoyalIndigo = Color(0xFF1E1B4B)
val CyberViolet = Color(0xFF7B2CBF)
val NeonPurple = Color(0xFF9D4EDD)

// Status & Indicators
val LivePulsingRed = Color(0xFFFF334B)
val EnergyOrange = Color(0xFFFF5722)
val VictoryGreen = Color(0xFF00E676)
val WarningYellow = Color(0xFFFFB300)

// Medals & Badges
val GoldMedal = Color(0xFFFFD700)
val SilverMedal = Color(0xFFC0C0C0)
val BronzeMedal = Color(0xFFCD7F32)

// Typography
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF94A3B8)
val TextMuted = Color(0xFF64748B)
val TextDark = Color(0xFF06090F)

// Button & Pill States (Matching Reference Image)
val PillActiveBackground = Color(0xFFFFFFFF)
val PillActiveText = Color(0xFF070A10)
val PillInactiveBackground = Color(0x40162338)
val PillInactiveText = Color(0xFFCBD5E1)

// Premium Gradient Brushes
val HeroGlassGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xEE111E33),
        Color(0xCC0A1220)
    )
)

val CardGlowGradient = Brush.linearGradient(
    colors = listOf(
        Color(0x330066FF),
        Color(0x1100E5FF),
        Color(0x00000000)
    )
)

val AiConcentricGradient = Brush.radialGradient(
    colors = listOf(
        Color(0xFF2563EB),
        Color(0xFF1E3A8A),
        Color(0xFF0B1426),
        Color(0x0006090F)
    )
)

val LiveBadgeGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFFFF334B),
        Color(0xFFFF5722)
    )
)

val ElectricButtonGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF2B7FFF),
        Color(0xFF00E5FF)
    )
)

val GoldTrophyGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFFFFE259),
        Color(0xFFFFA751)
    )
)
