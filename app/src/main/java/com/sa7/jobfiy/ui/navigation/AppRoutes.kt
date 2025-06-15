package com.sa7.jobfiy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sa7.jobfiy.authentication.ui.screens.login.LoginScreen
import com.sa7.jobfiy.authentication.ui.screens.login.LoginViewModel
import com.sa7.jobfiy.authentication.ui.screens.signUp.SignUpScreen
import com.sa7.jobfiy.ui.screens.HomeScreen.HomeScreenViewModel
import com.sa7.jobfiy.ui.screens.HomeScreen.JobifyScreen
import com.sa7.jobfiy.ui.screens.JobDescriptionScreen.JobDetailPage
import com.sa7.jobfiy.ui.screens.JobDescriptionScreen.JobDescriptionViewModel
import com.sa7.jobfiy.ui.fragments.JobSearchFragment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember
import com.sa7.jobfiy.authentication.data.repository.AuthRepository

sealed class AppRoutes(val route: String) {
    object LOGIN : AppRoutes("login")
    object SIGN_UP : AppRoutes("signup")
    object HOME : AppRoutes("home")
    object JOB_DESCRIPTION : AppRoutes("job_description/{jobId}") {
        fun createRoute(jobId: String) = "job_description/$jobId"
    }
    object JOB_APPLICATION : AppRoutes("job_application/{jobId}") {
        fun createRoute(jobId: String) = "job_application/$jobId"
    }
    object JOB_SEARCH : AppRoutes("job_search")
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel
) {
    val navController = rememberNavController()
    val startDest = AppRoutes.LOGIN.route
    
    NavHost(
        navController = navController,
        startDestination = startDest,
        modifier = modifier
    ) {
        composable(AppRoutes.LOGIN.route) {
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(AppRoutes.SIGN_UP.route) {
            SignUpScreen(navController = navController)
        }
        composable(AppRoutes.HOME.route) {
            JobifyScreen(navController = navController, viewModel = androidx.lifecycle.viewmodel.compose.viewModel<HomeScreenViewModel>())
        }
        composable(AppRoutes.JOB_DESCRIPTION.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            requireNotNull(jobId) { "jobId parameter wasn't found. Please make sure it's set!" }
            JobDetailPage(
                jobId = jobId,
                viewModel = androidx.lifecycle.viewmodel.compose.viewModel<HomeScreenViewModel>(),
                navController = navController
            )
        }
        composable(AppRoutes.JOB_APPLICATION.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            requireNotNull(jobId) { "jobId parameter wasn't found. Please make sure it's set!" }
            // TODO: Add JobApplicationScreen composable
            // JobApplicationScreen(jobId = jobId, navController = navController)
        }
        composable(AppRoutes.JOB_SEARCH.route) {
            JobSearchFragment(navController = navController)
        }
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