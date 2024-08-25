package com.ivoafsilva.mobiweb.mobileapp.domain

/**
 * Defines the States ViewModels may send to the UI
 */
sealed class UiState {
    data object Idle : UiState()

    data object Loading : UiState()

    data object Success : UiState()

    data object Error : UiState()
}