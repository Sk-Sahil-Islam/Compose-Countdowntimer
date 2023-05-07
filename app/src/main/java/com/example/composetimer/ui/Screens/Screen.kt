package com.example.composetimer.ui.Screens

sealed class Screen(val route: String){
    object HomeScreen: Screen("home_screen")
    object TimerScreen: Screen("timer_screen")
}
