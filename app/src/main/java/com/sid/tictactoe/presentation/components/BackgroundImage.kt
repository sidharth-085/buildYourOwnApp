package com.sid.tictactoe.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sid.tictactoe.R

@Composable
fun BackgroundImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.xo_background),
        contentDescription = "background_image",
        contentScale = ContentScale.Crop
    )
}