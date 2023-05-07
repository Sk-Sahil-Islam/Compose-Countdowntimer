package com.example.composetimer


data class ValueState (
    val hours: Long = 0,
    val minutes: Long = 0,
    val seconds: Long = 0,
    val initialTimerInMillis: Long = 0,
    val isTimerPlaying: Boolean = false,
    val timerText: String = "",
    val showTimer: Boolean = false
)