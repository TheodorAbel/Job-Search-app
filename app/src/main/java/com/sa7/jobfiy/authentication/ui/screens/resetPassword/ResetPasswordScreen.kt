package com.sa7.jobfiy.authentication.ui.screens.resetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sa7.jobfiy.R
import com.sa7.jobfiy.authentication.domain.model.LoginUiEvent
import com.sa7.jobfiy.authentication.ui.component.ButtonComponent
import com.sa7.jobfiy.authentication.ui.component.TextFieldComponent
import com.sa7.jobfiy.authentication.ui.screens.login.LoginViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.setValue

@Composable
fun ResetPasswordScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Reset password screen content
                TextFieldComponent(
                    labelValue = "Email",
                    icon = Icons.Default.Email,
                    onTextSelected = { email ->
                        loginViewModel.onEvent(LoginUiEvent.EmailChanged(email))
                    },
                    errorStatus = false // or your error logic
                )

                Spacer(modifier = Modifier.heightIn(16.dp))

                ButtonComponent(
                    value = "Reset Password",
                    onButtonClick = {
                        loginViewModel.onEvent(LoginUiEvent.ResetPasswordButtonClicked(email))
                    }
                )
            }

        }
    }
}