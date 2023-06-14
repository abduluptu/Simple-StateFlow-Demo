package com.abdul.simplestateflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer()
    }
}

// Step1: Producer produces/emits the values
private fun producer(): StateFlow<Int> { // Flow<Int> can be used
    val mutableStateFlow = MutableStateFlow(1)
    GlobalScope.launch {
        delay(1000)
        mutableStateFlow.emit(2)
        delay(1000)
        mutableStateFlow.emit(3)
    }
    return mutableStateFlow
}

// Step2: Consumer consumes/collects the values
private fun consumer() {
    GlobalScope.launch {
        val result = producer()
        result.collect {
            Log.d("StateFlow", "Item1 - $it")
        }
        // Log.d("StateFlow", "Item1 - ${result.value}")
    }
}