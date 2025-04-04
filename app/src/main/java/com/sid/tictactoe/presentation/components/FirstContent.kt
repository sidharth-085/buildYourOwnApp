package com.sid.tictactoe.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.sid.tictactoe.R

@Composable
fun FirstContent(
    modifier: Modifier = Modifier,
    onSubmit: (isAi: Boolean) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Spacer(Modifier.weight(0.50f))
        Text(
            stringResource(R.string.how_do_you_want_to_play),
            style = MaterialTheme.typography.bodyMedium.plus(
                TextStyle(
                    fontSize = 18.sp
                )
            ),
            modifier = Modifier
                .weight(0.25f)
                .wrapContentSize()
        )
        Row(
            modifier = Modifier.weight(0.30f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomButton(
                title = stringResource(R.string.single_player),
                onPressed = {
                    onSubmit(true)
                }
            )
        }
        Row(
            modifier = Modifier.weight(0.30f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomButton(
                title = stringResource(R.string.play_with_a_friend),
                onPressed = {
                    onSubmit(false)
                },
            )
        }
        Spacer(Modifier.weight(0.10f))
    }
}