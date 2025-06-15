package com.sa7.jobfiy.authentication.ui.screens.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sa7.jobfiy.authentication.ui.component.ButtonComponent
import com.sa7.jobfiy.authentication.ui.component.ClickableTextComponent
import com.sa7.jobfiy.authentication.ui.component.DividerTextComponent
import com.sa7.jobfiy.authentication.ui.component.GoogleButtonComponent
import com.sa7.jobfiy.authentication.ui.component.HeadingTextComponent
import com.sa7.jobfiy.authentication.ui.component.PasswordTextFieldComponent
import com.sa7.jobfiy.authentication.ui.component.TextFieldComponent
import com.sa7.jobfiy.authentication.ui.component.NormalTextComponent
import com.sa7.jobfiy.ui.navigation.AppRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, signUpViewModel: SignUpViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                modifier = Modifier.fillMaxSize(),
            ) {
                NormalTextComponent("Hey there,")
                HeadingTextComponent("Create an account")

                // First Name Field
                TextFieldComponent(
                    labelValue = "First Name",
                    icon = Icons.Default.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.FirstNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.firstNameError,
                    errorMessage = signUpViewModel.registrationUiState.value.nameErrorMessage
                )

                // Last Name Field
                TextFieldComponent(
                    labelValue = "Last Name",
                    icon = Icons.Default.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.LastNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.lastNameError,
                    errorMessage = signUpViewModel.registrationUiState.value.nameErrorMessage
                )

                // Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Confirm Password Field
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )
                Spacer(modifier = Modifier.heightIn(80.dp))
                ButtonComponent(
                    value = "Sign Up",
                    onButtonClick = {
                        signUpViewModel.onEvent(SignUpUiEvent.RegisterButtonClicked)
                        if(signUpViewModel.registrationCompleted.value==true){
                            navController.navigate(AppRoutes.HOME.route)
                        }
                    }
                )
                Spacer(modifier = Modifier.heightIn(10.dp))
                DividerTextComponent()
                Spacer(modifier = Modifier.heightIn(10.dp))
                GoogleButtonComponent()
                ClickableTextComponent(
                    value = "Already have an account? Sign In",
                    onButtonClick = {
                        navController.navigate(AppRoutes.LOGIN.route)
                    }
                )
            }
        }

        // Progress Indicator to show the registration progress
        if (signUpViewModel.registrationProgress.value) {
            CircularProgressIndicator(
                color = Color.Black,
                strokeWidth = 5.dp
            )
        }
    }
}
