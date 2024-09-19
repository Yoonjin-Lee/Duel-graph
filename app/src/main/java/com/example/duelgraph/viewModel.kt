package com.example.duelgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val _state = MutableSharedFlow<Int>()
    val state = _state.asSharedFlow()

    // ScrollGraph와 LineGraph가 서로 인덱스를 주고 받는 함수
    fun updateState(
        int: Int
    ) {
        viewModelScope.launch {
            _state.emit(int)
        }
    }
}