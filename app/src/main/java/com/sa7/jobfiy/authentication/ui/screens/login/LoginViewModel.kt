package com.sa7.jobfiy.authentication.ui.screens.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.sa7.jobfiy.authentication.Validator
import androidx.lifecycle.viewModelScope
import com.sa7.jobfiy.authentication.data.repository.AuthRepository
import com.sa7.jobfiy.authentication.domain.model.LoginUiState
import com.sa7.jobfiy.authentication.domain.model.LoginUiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    private val emailId = MutableLiveData<String>()
    val emailIdLiveData: LiveData<String> = emailId

    init {
        // Check if user is already logged in
        _isUserLoggedIn.value = authRepository.isUserLoggedIn()
    }

    // Handle the events from the UI
    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                _loginUiState.update { currentState ->
                    currentState.copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }
            is LoginUiEvent.PasswordChanged -> {
                _loginUiState.update { currentState ->
                    currentState.copy(
                        password = event.password,
                        passwordError = null
                    )
                }
            }
            is LoginUiEvent.LoginButtonClicked -> {
                loginUser()
            }
            is LoginUiEvent.ResetPasswordButtonClicked -> {
                resetPassword(event.email)
            }
        }
    }

    private fun loginUser() {
        viewModelScope.launch {
            _loginUiState.update { it.copy(isLoading = true) }

            try {
                val email = _loginUiState.value.email
                val password = _loginUiState.value.password

                // Validate input
                if (email.isBlank()) {
                    _loginUiState.update {
                        it.copy(
                            emailError = "Email cannot be empty",
                            isLoading = false
                        )
                    }
                    return@launch
                }

                if (password.isBlank()) {
                    _loginUiState.update {
                        it.copy(
                            passwordError = "Password cannot be empty",
                            isLoading = false
                        )
                    }
                    return@launch
                }

                // Attempt login
                val result = authRepository.login(email, password)
                if (result.isSuccess) {
                    _isUserLoggedIn.value = true
                    emailId.value = email
                } else {
                    _loginUiState.update {
                        it.copy(
                            error = result.exceptionOrNull()?.message ?: "Login failed",
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _loginUiState.update {
                    it.copy(
                        error = e.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun resetPassword(email: String) {
        viewModelScope.launch {
            try {
                // TODO: Implement password reset using Firebase Auth
                Log.d("LoginViewModel", "Password reset requested for: $email")
            } catch (e: Exception) {
                _loginUiState.update {
                    it.copy(
                        error = e.message ?: "Failed to send reset email",
                        isLoading = false
                    )
                }
            }
        }
    }

    // State of the validation
    private var allValidationPassed = mutableStateOf(false)

    private fun validResetPasswordEmail() {
        val email = Validator.isValidEmail(_loginUiState.value.email)
        _loginUiState.update { currentState ->
            currentState.copy(
                emailError = if (!email.status) "Invalid email" else null
            )
        }
        allValidationPassed.value = email.status
    }
}

class LoginViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}