package com.news.compose.sample.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.news.compose.core.R

val Onest = FontFamily(
    Font(R.font.onest_bold, FontWeight.Bold),
    Font(R.font.onest_regular, FontWeight.Normal),
    Font(R.font.onest_medium, FontWeight.Medium)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.33).sp
    ),
    titleMedium = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.33).sp
    ),
    titleSmall = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.33).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.33).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.33).sp
    ),
    bodySmall = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.33).sp
    ),
    labelLarge = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.33).sp
    ),
    labelMedium = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.33).sp
    ),
    labelSmall = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.33).sp
    ),
)
