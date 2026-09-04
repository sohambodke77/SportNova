package com.sportnova.app.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sportnova.app.components.CircularIconButton
import com.sportnova.app.components.GlassCard
import com.sportnova.app.components.WhitePillButton
import com.sportnova.app.model.DigitalCertificate
import com.sportnova.app.theme.*

/**
 * Official Digital Sports Certificate Preview Modal
 * Premium gold-foiled certificate with verification seal, student details, QR code, and actions.
 */
@Composable
fun DigitalCertificateDialog(
    certificate: DigitalCertificate,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        GlassCard(
            cornerRadius = 24.dp,
            borderStrokeColor = GoldMedal.copy(alpha = 0.5f),
            backgroundColor = Color(0xF7070D18),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "Verified",
                            tint = VictoryGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "VERIFIED CREDENTIAL",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = VictoryGreen,
                                letterSpacing = 0.8.sp
                            )
                        )
                    }

                    CircularIconButton(
                        icon = Icons.Default.Close,
                        contentDescription = "Close",
                        onClick = onDismiss,
                        size = 34.dp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Certificate Frame (Authentic Gold Certificate Look)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF0C1422))
                        .border(2.dp, GoldMedal, RoundedCornerShape(16.dp))
                        .padding(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, GoldMedal.copy(alpha = 0.4f), RoundedCornerShape(13.dp))
                            .padding(18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Gold Crest / Icon
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.linearGradient(
                                            listOf(GoldMedal, Color(0xFFFFA000))
                                        )
                                    )
                            ) {
                                Text(
                                    text = "SN",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Black,
                                        color = BackgroundDark
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "SPORTNOVA ATHLETIC FEDERATION",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 1.sp,
                                    fontSize = 9.sp,
                                    color = GoldMedal
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "CERTIFICATE OF EXCELLENCE",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 0.5.sp,
                                    fontSize = 15.sp,
                                    color = TextPrimary
                                )
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "This is proudly conferred to",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Student Name
                            Text(
                                text = certificate.studentName,
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Black,
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily.Serif,
                                    color = Color.White
                                )
                            )

                            Text(
                                text = "of ${certificate.collegeName}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    color = NeonCyan
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "For outstanding performance as\n${certificate.rankOrRole}\nin the prestigious tournament:",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    lineHeight = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = TextSecondary
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = certificate.eventName,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center,
                                    color = GoldMedal
                                )
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            // Date and ID Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Date Issued:",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 9.sp,
                                            color = TextSecondary
                                        )
                                    )
                                    Text(
                                        text = certificate.dateIssued,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = 10.sp,
                                            color = TextPrimary
                                        )
                                    )
                                }

                                // QR Code icon placeholder
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color.White)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.QrCode2,
                                        contentDescription = "QR Verification",
                                        tint = Color.Black,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }

                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "Certificate ID:",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 9.sp,
                                            color = TextSecondary
                                        )
                                    )
                                    Text(
                                        text = certificate.certificateNumber.takeLast(8),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = NeonCyan
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Actions: Download PDF (White Pill) & Share
                WhitePillButton(
                    text = "Download PDF Certificate",
                    onClick = onDismiss
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = TextPrimary
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(listOf(GlassBorderLight, GlassBorderBlue))
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Share Certificate Verification Link",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    )
                }
            }
        }
    }
}
