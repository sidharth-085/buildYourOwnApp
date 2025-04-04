package com.sid.tictactoe.presentation.components

import android.view.MotionEvent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomButton(
    title: String,
    onPressed: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }
    Box(
        Modifier
            .height(
                62.dp
            )
            .fillMaxWidth(
                0.89f
            )
            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isPressed = true
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        isPressed = false
                        onPressed()
                    }
                }
                true
            },
    ) {
        Surface(
            modifier = Modifier
                .height(
                    56.dp
                )
                .fillMaxWidth(0.98f)
                .align(if (isPressed.not()) Alignment.BottomStart else Alignment.Center)
        ) {
        }
        Surface(
            modifier = Modifier
                .height(
                    56.dp
                )
                .fillMaxWidth(0.98f)
                .align(if (isPressed.not()) Alignment.TopEnd else Alignment.Center),
            color = MaterialTheme.colorScheme.secondary,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            Surface(
                modifier = Modifier
                    .size(
                        width = 344.dp,
                        height = 56.dp
                    )
                    .align(Alignment.TopEnd)
                    .padding(4.dp),
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.bodyLarge.plus(
                        TextStyle(
                            color = MaterialTheme.colorScheme.background
                        )
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
        }
    }
}
