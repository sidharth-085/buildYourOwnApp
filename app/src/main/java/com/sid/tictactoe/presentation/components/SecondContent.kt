package com.sid.tictactoe.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Elderly
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sid.tictactoe.R
import com.sid.tictactoe.data.model.AILevel

@Composable
fun SecondContent(
    modifier: Modifier = Modifier,
    isAi: Boolean,
    onSubmit: (isPro: Boolean, selectedAiLevel: Int?) -> Unit,
    onBackPressed: () -> Unit
) {
    var isPro by remember { mutableStateOf(false) }
    var selectedAiLevel by remember { mutableStateOf(0) }


    Box {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp),
            onClick = onBackPressed
        ) {
            Icon(
                Icons.Filled.ArrowBackIosNew,
                contentDescription = "back button"
            )
        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(if (isAi) 0.15f else 0.50f))
            Text(
                stringResource(R.string.select_mode),
                style = MaterialTheme.typography.bodyMedium.plus(
                    TextStyle(
                        fontSize = 18.sp
                    )
                ),
                modifier = Modifier.weight(0.10f)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .weight(0.40f)
            ) {
                FilterChip(
                    modifier = Modifier
                        .size(
                            100.dp,
                            50.dp
                        )
                        .weight(1f),
                    selected = isPro.not(),
                    onClick = {
                        isPro = false
                    },
                    label = { Text(text = stringResource(R.string.classic)) },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.White,
                        selectedLabelColor = Color.White,
                        selectedContainerColor = Color.Black,
                        selectedLeadingIconColor = Color.White,
                        iconColor = Color.Black,
                        labelColor = Color.Black,
                    ),
                    leadingIcon = {
                        Icon(
                            contentDescription = "Classic Icon",
                            imageVector = Icons.Filled.Elderly,
                            modifier = Modifier.size(
                                width = 30.dp,
                                height = 30.dp
                            )
                        )
                    },
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                )
                Spacer(Modifier.weight(0.5f))
                FilterChip(
                    modifier = Modifier
                        .size(
                            100.dp,
                            50.dp
                        )
                        .weight(1f),
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    selected = isPro,
                    onClick = {
                        isPro = true
                    },
                    label = { Text(text = stringResource(R.string.pro)) },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.White,
                        selectedLabelColor = Color.White,
                        selectedContainerColor = Color.Black,
                        selectedLeadingIconColor = Color.White,
                        iconColor = Color.Black,
                        labelColor = Color.Black,
                    ),
                    leadingIcon = {
                        Icon(
                            contentDescription = "Pro Icon",
                            imageVector = Icons.Filled.Person4,
                            modifier = Modifier.size(
                                width = 30.dp,
                                height = 30.dp
                            ),
                        )
                    }
                )
            }

            if (isAi) {
                val iconList = listOf<ImageVector>(
                    Icons.Filled.ChildCare,
                    Icons.Filled.SmartToy,
                    Icons.Filled.Psychology
                )
                Text(
                    stringResource(R.string.select_ai_difficulty),
                    style = MaterialTheme.typography.bodyMedium.plus(
                        TextStyle(
                            fontSize = 18.sp
                        )
                    ),
                    modifier = Modifier.weight(0.10f)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .weight(0.40f)
                ) {
                    val configuration = LocalConfiguration.current
                    val screenWidthDp = configuration.screenWidthDp
                    AILevel.entries.forEachIndexed { index, aiLevel ->
                        FilterChip(
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
                                .weight(0.3f)
                                .padding(horizontal = 5.dp),
                            selected = index == selectedAiLevel,
                            onClick = {
                                selectedAiLevel = index
                            },
                            label = {
                                Text(
                                    text = aiLevel.name,
                                    fontSize = if (screenWidthDp < 500) (screenWidthDp * 0.026).sp else 10.sp,
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    contentDescription = "",
                                    imageVector = iconList[index]
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color.White,
                                selectedLabelColor = Color.White,
                                selectedContainerColor = Color.Black,
                                selectedLeadingIconColor = Color.White,
                                iconColor = Color.Black,
                                labelColor = Color.Black,
                            ),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                            ),
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.weight(0.40f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                CustomButton(
                    title = stringResource(R.string.start_game),
                    onPressed = {
                        onSubmit(
                            isPro,
                            if (isAi) selectedAiLevel else -1
                        )
                    },
                )
            }
        }
    }
}