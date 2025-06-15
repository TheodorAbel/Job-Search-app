package com.sa7.jobfiy.authentication.domain.model

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val error: String? = null,
    val isLoading: Boolean = false
) 