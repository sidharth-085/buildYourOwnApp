package com.sid.tictactoe.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun GameTitle(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                "Tic",
                style = MaterialTheme.typography.titleLarge.plus(
                    TextStyle(
                        color = Color.Black,
                        fontSize = 66.sp,
                    )
                ),
                modifier = Modifier
                    .weight(0.25f)
                    .wrapContentSize(Alignment.Center)
            )
            Text(
                "Tac",
                style = MaterialTheme.typography.titleLarge.plus(
                    TextStyle(
                        color = Color.Black,
                        fontSize = 86.sp,
                    )
                ),
                modifier = Modifier
                    .weight(0.25f)
                    .wrapContentSize(Alignment.Center)
            )
            Text(
                "Toe",
                style = MaterialTheme.typography.titleLarge.plus(
                    TextStyle(
                        color = Color.Black,
                        fontSize = 96.sp,
                    )
                ),
                modifier = Modifier
                    .weight(0.25f)
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}