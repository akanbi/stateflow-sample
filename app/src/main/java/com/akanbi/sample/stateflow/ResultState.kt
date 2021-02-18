package com.akanbi.sample.stateflow

sealed class ResultState {
    object Success: ResultState()
    object Error: ResultState()
    object Loading: ResultState()
    object Initial: ResultState()
}