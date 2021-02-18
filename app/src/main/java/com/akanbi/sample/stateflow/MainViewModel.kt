package com.akanbi.sample.stateflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private var errorFlow = false
    private var _uiStateFlow = MutableStateFlow<ResultState>(ResultState.Initial)
    val uiStateFlow: StateFlow<ResultState> get() = _uiStateFlow


    fun doSomething() = viewModelScope.launch {
        _uiStateFlow.value = ResultState.Loading
        delay(5000L)

        errorFlow = Random.nextBoolean()
        _uiStateFlow.value = if (errorFlow) ResultState.Error
        else ResultState.Success
    }

}