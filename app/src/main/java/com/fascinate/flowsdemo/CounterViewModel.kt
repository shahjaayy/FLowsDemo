package com.fascinate.flowsdemo

import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CounterViewModel : ViewModel() {

    val startCounter = flow<Int> {
        val startingValue = 100
        var updatedValue = startingValue

        emit(startingValue)

        while(updatedValue > 0)
        {
            delay(500L)
            updatedValue--
            emit(updatedValue)
        }
    }
}