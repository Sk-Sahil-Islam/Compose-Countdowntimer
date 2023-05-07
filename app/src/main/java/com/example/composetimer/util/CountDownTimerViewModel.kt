package com.example.composetimer.util

import android.os.CountDownTimer
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetimer.TimerEvent
import com.example.composetimer.ValueState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CountDownTimerViewModel: ViewModel() {
    private var countDownTimer: CountDownTimer? = null

    private val _state = MutableStateFlow(ValueState())
    val state = _state.asStateFlow()

    fun onEvent(event: TimerEvent){
        when(event){
            TimerEvent.StartCountDownTimer -> {
                val hour = TimeUnit.HOURS.toMillis(_state.value.hours)
                val minute = TimeUnit.MINUTES.toMillis(_state.value.minutes)
                val second = TimeUnit.SECONDS.toMillis(_state.value.seconds)
                val initialTotalTimeInMillis = hour + minute + second
                var timeLeft = initialTotalTimeInMillis

                _state.update { it.copy(
                    isTimerPlaying = true,
                ) }
                countDownTimer = object : CountDownTimer(timeLeft, 1000L) {
                    override fun onTick(remaining: Long) {
                        _state.update { it.copy(
                            hours = TimeUnit.MILLISECONDS.toHours(remaining),
                            minutes = TimeUnit.MILLISECONDS.toMinutes(remaining) % 60,
                            seconds = TimeUnit.MILLISECONDS.toSeconds(remaining) % 60,
                            timerText = remaining.timeFormat()
                        ) }
                        timeLeft = remaining
                    }

                    override fun onFinish() {
                        _state.update { it.copy(
                            timerText = "Done!",
                            isTimerPlaying = false,
                            showTimer = false
                        ) }
                    }
                }.start()
            }
            TimerEvent.StopCountDownTimer -> {
                _state.update { it.copy(
                    isTimerPlaying = false
                ) }
                countDownTimer?.cancel()
            }
            TimerEvent.ResetCountDownTimer -> {
                _state.update { it.copy(
                    isTimerPlaying = false
                ) }
                countDownTimer?.cancel()
                _state.update { it.copy(
                    hours = TimeUnit.MILLISECONDS.toHours(_state.value.initialTimerInMillis),
                    minutes = TimeUnit.MILLISECONDS.toMinutes(_state.value.initialTimerInMillis) % 60,
                    seconds = TimeUnit.MILLISECONDS.toSeconds(_state.value.initialTimerInMillis) % 60,
                    initialTimerInMillis = 0
                ) }
            }
            is TimerEvent.SetHour -> {
                _state.update { it.copy(
                    hours = event.hour
                ) }
            }
            is TimerEvent.SetMinute -> {
                _state.update { it.copy(
                    minutes = event.minute
                ) }
            }
            is TimerEvent.SetSecond -> {
                _state.update { it.copy(
                    seconds = event.second
                ) }
            }
            is TimerEvent.OnSubmit -> {
                _state.update {it.copy(
                    initialTimerInMillis =TimeUnit.HOURS.toMillis(_state.value.hours)
                    + TimeUnit.MINUTES.toMillis(_state.value.minutes)
                    + TimeUnit.SECONDS.toMillis(_state.value.seconds),
                ) }
            }
        }
    }
}

fun Long.timeFormat(): String = String.format(
    "%02d:%02d:%02d",
    TimeUnit.MILLISECONDS.toHours(this),
    TimeUnit.MILLISECONDS.toMinutes(this) % 60,
    TimeUnit.MILLISECONDS.toSeconds(this) % 60,
)