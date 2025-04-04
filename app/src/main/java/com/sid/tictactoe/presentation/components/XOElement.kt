package com.sid.tictactoe.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.sid.tictactoe.presentation.theme.accent1
import com.sid.tictactoe.presentation.theme.accent2
import com.sid.tictactoe.presentation.theme.accent3

@Composable
fun XOElement(
    isBlinking: Boolean,
    isX: Boolean,
    isAi: Boolean,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "",
    )
    val currentAlpha = if (isBlinking) alpha else 1f
    val density = LocalConfiguration.current.densityDpi


    Text(
        if (isX) "X" else "O",
        style = MaterialTheme.typography.headlineLarge.plus(
            TextStyle(
                fontSize = (density * 0.17).sp,
            )
        ),
        color = if (isX) (if (isAi) accent2 else MaterialTheme.colorScheme.primary) else (if (isAi) accent3 else accent1),
        modifier = Modifier
            .graphicsLayer(alpha = currentAlpha)
            .wrapContentSize()
    )
}