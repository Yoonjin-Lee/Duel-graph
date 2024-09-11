package com.example.duelgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val _state = MutableSharedFlow<Int>()
    val state = _state.asSharedFlow()

    fun updateState(
        int: Int
    ) {
        viewModelScope.launch {
            _state.emit(int)
        }
    }
}