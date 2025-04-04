package com.sid.tictactoe.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sid.tictactoe.R
import com.sid.tictactoe.domain.model.Move
import com.sid.tictactoe.presentation.screens.game.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Board(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
) {
    val isTurnX by viewModel.isTurnX.collectAsState()
    val board by viewModel.xoList.collectAsState()
    val isGameFinished by viewModel.isGameFinished.collectAsState()
    val isGoingToDeleteList by viewModel.isGoingToDeleteList.collectAsState()
    val xWins by viewModel.xWins.collectAsState()
    val oWins by viewModel.oWins.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            if (viewModel.isAi) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .alpha(if (isTurnX) 0.25f else 1f)
                ) {
                    Text(
                        stringResource(
                            R.string.you_o,
                            oWins
                        ),
                        style = MaterialTheme.typography.bodyMedium.plus(
                            TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = (screenWidthDp * 0.042).sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        ),
                    )
                    Image(
                        painter = painterResource(R.drawable.self_ai_image),
                        contentDescription = "Self Image",
                        modifier = Modifier
                            .size(
                                (screenWidthDp * 0.26).dp,
                                (screenWidthDp * 0.26).dp
                            ),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (viewModel.isAi.not()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .alpha(if (isTurnX) 0.25f else 1f),
                ) {
                    Text(
                        stringResource(
                            R.string.player_o,
                            oWins
                        ),
                        style = MaterialTheme.typography.bodyMedium.plus(
                            TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = (screenWidthDp * 0.042).sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        ),
                    )
                    Image(
                        painter = painterResource(R.drawable.self_image),
                        contentDescription = "Self Image",
                        modifier = Modifier
                            .size(
                                (screenWidthDp * 0.26).dp,
                                (screenWidthDp * 0.26).dp
                            )
                            .graphicsLayer(
                                scaleX = -1f
                            ),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            if (viewModel.isAi) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .alpha(if (isTurnX.not()) 0.25f else 1f)
                ) {
                    Text(
                        stringResource(
                            R.string.game_ai,
                            xWins
                        ),
                        style = MaterialTheme.typography.bodyMedium.plus(
                            TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = (screenWidthDp * 0.042).sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        ),
                    )
                    Image(
                        painter = painterResource(R.drawable.ai_image),
                        contentDescription = "Ai Image",
                        modifier = Modifier
                            .size(
                                (screenWidthDp * 0.26).dp,
                                (screenWidthDp * 0.26).dp
                            )
                            .graphicsLayer(
                                scaleX = -1f
                            ),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .width((screenWidthDp * 0.75).dp)
                .aspectRatio(1f)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth(0.96f)
                    .aspectRatio(1f)
                    .align(Alignment.BottomStart)
            ) {
            }

            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                columns = GridCells.Fixed(count = 3),
                userScrollEnabled = false,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth(0.96f)
                    .aspectRatio(1f)
                    .align(Alignment.TopEnd)
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                        ),
                    ),
            ) {
                items(board.flatten().size) { index ->
                    Surface(
                        color = MaterialTheme.colorScheme.secondary,
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable {
                                val row = index / 3
                                val column = index % 3
                                if (board[row][column] != '_' || isGameFinished || (if (viewModel.isAi) isTurnX else false)) {
                                    return@clickable
                                }
                                CoroutineScope(Dispatchers.Main).launch {
                                    viewModel.performNewMove(
                                        Move(
                                            row = row,
                                            column = column
                                        )
                                    )
                                }
                            },
                    ) {
                        val row = index / 3
                        val column = index % 3
                        when (board[row][column]) {
                            'X' -> {
                                XOElement(
                                    isX = true,
                                    isBlinking = isGoingToDeleteList[row][column],
                                    isAi = viewModel.isAi,
                                )
                            }

                            'O' -> {
                                XOElement(
                                    isX = false,
                                    isBlinking = isGoingToDeleteList[row][column],
                                    isAi = viewModel.isAi,
                                )
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .alpha(if (viewModel.isAi.not()) (if (isTurnX.not()) 0.25f else 1f) else 0f)
            ) {
                Image(
                    painter = painterResource(R.drawable.opponent_image),
                    contentDescription = "Opponent Image",
                    modifier = Modifier
                        .size(
                            (screenWidthDp * 0.26).dp,
                            (screenWidthDp * 0.26).dp
                        )
                        .graphicsLayer(
                            rotationZ = 180f,
                            scaleX = -1f,
                        ),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    stringResource(
                        R.string.player_x,
                        xWins
                    ),
                    modifier = Modifier.rotate(180f),
                    style = MaterialTheme.typography.bodyMedium.plus(
                        TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = (screenWidthDp * 0.042).sp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    ),
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}