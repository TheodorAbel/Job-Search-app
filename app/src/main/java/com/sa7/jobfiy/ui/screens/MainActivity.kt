package com.sa7.jobfiy.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sa7.jobfiy.ui.fragments.*
import com.sa7.jobfiy.ui.theme.JobfiyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobfiyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "job_search") {
        composable("job_search") {
            JobSearchFragment(navController = navController)
        }
        composable("user_profile") {
            UserProfileFragment(
                onLogout = { /* Handle logout */ },
                firstName = "User",
                onUpdateProfile = { /* Handle profile update */ }
            )
        }
        composable("application_status") {
            ApplicationStatusFragment(
                applications = listOf(
                    ApplicationStatus(
                        jobTitle = "Senior Software Engineer",
                        company = "Ethio Telecom",
                        status = ApplicationStatus.Status.PENDING,
                        date = "2024-03-15"
                    ),
                    ApplicationStatus(
                        jobTitle = "Mobile App Developer",
                        company = "Dashen Bank",
                        status = ApplicationStatus.Status.INTERVIEW,
                        date = "2024-03-14"
                    )
                )
            )
        }
        composable("job_application/{jobId}") { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            jobId?.let {
                ApplicationForm(
                    onDismiss = { navController.popBackStack() },
                    onSubmit = { applicationData ->
                        // Handle application submission
                        navController.popBackStack()
                    }
                )
            }
        }
    }
} 