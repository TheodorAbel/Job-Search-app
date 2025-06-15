package com.sa7.jobfiy.authentication.domain.model

sealed class LoginUiEvent {
    data class EmailChanged(val email: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    object LoginButtonClicked : LoginUiEvent()
    data class ResetPasswordButtonClicked(val email: String) : LoginUiEvent()
} 