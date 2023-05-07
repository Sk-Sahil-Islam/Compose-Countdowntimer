package com.example.composetimer

sealed interface TimerEvent {
    data class SetHour(val hour: Long): TimerEvent
    data class SetMinute(val minute: Long): TimerEvent
    data class SetSecond(val second: Long): TimerEvent
    object StartCountDownTimer: TimerEvent
    object StopCountDownTimer: TimerEvent
    object ResetCountDownTimer: TimerEvent
    object OnSubmit: TimerEvent
}