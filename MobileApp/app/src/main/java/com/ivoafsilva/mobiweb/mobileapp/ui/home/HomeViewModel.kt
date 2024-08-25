package com.ivoafsilva.mobiweb.mobileapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivoafsilva.mobiweb.mobileapp.domain.UiState
import com.ivoafsilva.mobiweb.mobileapp.util.DispatcherProvider
import com.ivoafsilva.mobiweb.mobilesdk.MobileSdk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mobileSdk: MobileSdk,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text

    fun onTextChanged(newText: String) {
        _text.value = newText
    }

    fun onSaveClicked() {
        viewModelScope.launch(dispatcherProvider.io()) {
            Log.d(TAG, "Sending UI state=[Loading]")
            _uiState.value = UiState.Loading

            val result = mobileSdk.logMessage(text.value)

            if (result) {
                Log.d(TAG, "Sending UI state=[Success]")
                _uiState.value = UiState.Success
            } else {
                Log.d(TAG, "Sending UI state=[Error]")
                _uiState.value = UiState.Error
            }

            delay(2000) // Keep success or error state visible for a while
            Log.d(TAG, "Sending UI state=[Idle]")
            _uiState.value = UiState.Idle
        }
    }
}