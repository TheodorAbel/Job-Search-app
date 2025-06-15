package com.sa7.jobfiy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sa7.jobfiy.authentication.data.repository.AuthRepository
import com.sa7.jobfiy.authentication.ui.screens.login.LoginViewModel
import com.sa7.jobfiy.authentication.ui.screens.login.LoginViewModelFactory
import com.sa7.jobfiy.ui.navigation.AppNavHost
import com.sa7.jobfiy.ui.theme.JobfiyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel: LoginViewModel by viewModels {
            LoginViewModelFactory(AuthRepository())
        }
        setContent {
            JobfiyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(loginViewModel = loginViewModel)
                }
            }
        }
    }
}

