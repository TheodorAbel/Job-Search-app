package com.sa7.jobfiy.authentication.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sa7.jobfiy.authentication.domain.model.LoginUiEvent
import com.sa7.jobfiy.authentication.ui.component.*
import com.sa7.jobfiy.ui.navigation.AppRoutes
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val uiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()
    val isUserLoggedIn by loginViewModel.isUserLoggedIn.collectAsStateWithLifecycle()

    // Navigate to home screen when user is logged in
    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn) {
            navController.navigate(AppRoutes.HOME.route) {
                popUpTo(AppRoutes.LOGIN.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeadingTextComponent("Login to your account")

            // Email Field
            TextFieldComponent(
                value = uiState.email,
                labelValue = "Email",
                icon = rememberVectorPainter(Icons.Default.Email),
                onTextSelected = { email ->
                    loginViewModel.onEvent(LoginUiEvent.EmailChanged(email))
                },
                errorStatus = uiState.emailError != null,
                errorMessage = uiState.emailError
            )

            // Password Field
            PasswordTextFieldComponent(
                labelValue = "Password",
                onTextSelected = { password ->
                    loginViewModel.onEvent(LoginUiEvent.PasswordChanged(password))
                },
                errorStatus = uiState.passwordError != null,
                errorMessage = uiState.passwordError,
                icon = Icons.Default.Lock
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Login Button
            ButtonComponent(
                value = "Login",
                onButtonClick = {
                    loginViewModel.onEvent(LoginUiEvent.LoginButtonClicked)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Divider
            DividerTextComponent()

            Spacer(modifier = Modifier.height(10.dp))

            // Google Sign In
            GoogleButtonComponent()

            // Sign Up Option
            ClickableTextComponent(
                value = "Don't have an account? Sign Up",
                onButtonClick = {
                    navController.navigate(AppRoutes.SIGN_UP.route)
                }
            )
        }

        // Loading Indicator
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Error Message
        uiState.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}
