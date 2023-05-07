package com.example.composetimer.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetimer.TimerEvent
import com.example.composetimer.ValueState
import com.example.composetimer.ui.Screens.Screen


@Composable
fun HomeScreen(
    navController: NavController,
    state: ValueState,
    onEvent: (TimerEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xF0101010))
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(listOf(MyGreen, MyBlue)),
                    shape = RoundedCornerShape(12.dp)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = if (state.hours != 0L) state.hours.toString() else "",
                onValueChange = {
                    if (it.isNotBlank()) {
                        if (it.toLong() in 0..24) {
                            onEvent(TimerEvent.SetHour(it.toLong()))
                        }
                    } else {
                        onEvent(TimerEvent.SetHour(0))
                    }
                },
                label = {
                    Text(text = "Hours", fontWeight = FontWeight.Bold)
                },
                placeholder = {
                    Text(text = "Default: 0 hr", fontSize = 18.sp)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = MyGreen,
                    focusedLabelColor = MyBlue,
                    textColor = MyGreen,
                    placeholderColor = MyBlue,
                    disabledPlaceholderColor = MyGreen
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                modifier = Modifier.border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(listOf(MyGreen, MyBlue)),
                    shape = RoundedCornerShape(12.dp)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = if (state.minutes != 0L) state.minutes.toString() else "",
                onValueChange = {
                    if (it.isNotBlank()) {
                        if (it.toLong() in 0..60) {
                            onEvent(TimerEvent.SetMinute(it.toLong()))
                        }
                    } else {
                        onEvent(TimerEvent.SetMinute(0))
                    }
                },
                label = {
                    Text(text = "Minutes", fontWeight = FontWeight.Bold)
                },
                placeholder = {
                    Text(text = "Default: 0 min", fontSize = 18.sp)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = MyGreen,
                    focusedLabelColor = MyBlue,
                    textColor = MyGreen,
                    placeholderColor = MyBlue,
                    disabledPlaceholderColor = MyGreen
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                modifier = Modifier.border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(listOf(MyGreen, MyBlue)),
                    shape = RoundedCornerShape(12.dp)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onEvent(TimerEvent.OnSubmit)
                        onEvent(TimerEvent.StartCountDownTimer)
                        navController.navigate(Screen.TimerScreen.route) {
                            popUpTo(Screen.HomeScreen.route) { inclusive = true }
                        }
                    }
                ),
                value = if (state.seconds != 0L) state.seconds.toString() else "",
                onValueChange = {
                    if (it.isNotBlank()) {
                        if (it.toLong() in 0..60) {
                            onEvent(TimerEvent.SetSecond(it.toLong()))
                        }
                    } else {
                        onEvent(TimerEvent.SetSecond(0))
                    }
                },
                label = {
                    Text(text = "Seconds", fontWeight = FontWeight.Bold)
                },
                placeholder = {
                    Text(text = "Default: 0 sec", fontSize = 18.sp)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = MyGreen,
                    focusedLabelColor = MyBlue,
                    textColor = MyGreen,
                    placeholderColor = MyBlue,
                    disabledPlaceholderColor = MyGreen
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedButton(onClick = {
                onEvent(TimerEvent.OnSubmit)
                onEvent(TimerEvent.StartCountDownTimer)
                navController.navigate(Screen.TimerScreen.route) {
                    popUpTo(Screen.HomeScreen.route) { inclusive = true }
                }
            },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 25.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, MyBlue),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MyBlue,
                backgroundColor = Color.Transparent)
            ) {
                Text(text = "Submit")
            }
        }
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = "Note: Maximum value for hours, minutes, and seconds are 24, 60, 60 respectively.",
            color = Color.White,
        )
    }
}