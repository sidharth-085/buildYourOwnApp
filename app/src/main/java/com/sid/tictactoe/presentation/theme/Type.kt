package com.sid.tictactoe.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sid.tictactoe.R

val Array = FontFamily(
    Font(
        R.font.array_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.array_bold,
        FontWeight.Bold
    )
)
val DmSans = FontFamily(
    Font(
        R.font.dm_sans,
        FontWeight.Bold
    ),
    Font(
        R.font.dm_sans_regular,
        FontWeight.Normal
    ),
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Array,
        fontWeight = FontWeight.Bold,
        fontSize = 72.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Array,
        fontWeight = FontWeight.Normal,
        fontSize = 52.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Array,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)