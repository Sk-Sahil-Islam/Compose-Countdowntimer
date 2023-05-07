package com.example.composetimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetimer.ui.theme.*
import com.example.composetimer.ui.Screens.Screen
import com.example.composetimer.util.CountDownTimerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<CountDownTimerViewModel>()
            val state by viewModel.state.collectAsState()
            ComposeTimerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
                    composable(route = Screen.HomeScreen.route){
                        HomeScreen(navController = navController,state = state, onEvent = viewModel::onEvent)
                    }
                    composable(
                        route = Screen.TimerScreen.route
                    ){
                        TimerScreen(navController = navController, state = state, onEvent = viewModel::onEvent)
                    }
                }
            }
        }
    }
}


