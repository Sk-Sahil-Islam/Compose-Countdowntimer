package com.example.composetimer.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetimer.TimerEvent
import com.example.composetimer.ValueState
import com.example.composetimer.ui.Screens.Screen

@Composable
fun TimerScreen(
    navController: NavController,
    state: ValueState,
    onEvent: (TimerEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xF0101010)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = state.timerText,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = MyBlue
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            if (state.isTimerPlaying) onEvent(TimerEvent.StopCountDownTimer) else onEvent(TimerEvent.StartCountDownTimer)
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MyBlue,
                contentColor = Color(0xF0101010)
            ),
            modifier = Modifier.size(110.dp, 35.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = if (state.isTimerPlaying) "Stop Timer" else "Start Timer")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = {
            onEvent(TimerEvent.ResetCountDownTimer)
            navController.navigate(Screen.HomeScreen.route){
                popUpTo(Screen.TimerScreen.route){ inclusive = true }
            }
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MyBlue,
                contentColor = Color(0xF0101010)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Reset Timer")
        }
    }
}