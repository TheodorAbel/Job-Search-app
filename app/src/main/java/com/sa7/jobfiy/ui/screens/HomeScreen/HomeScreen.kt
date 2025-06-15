@file:OptIn(ExperimentalMaterial3Api::class)

package com.sa7.jobfiy.ui.screens.HomeScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sa7.jobfiy.R
import com.sa7.jobfiy.ui.commonUi.JobCard
import com.sa7.jobfiy.ui.commonUi.JobifyAppBar
import com.sa7.jobfiy.ui.commonUi.RadioButtonWithText
import com.sa7.jobfiy.ui.theme.Perpi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sa7.jobfiy.api.JobSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobifyScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val data by viewModel.data.observeAsState()
    val isLoading = viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.getJobsForCard("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jobify") },
                actions = {
                    IconButton(onClick = { /* TODO: Implement notifications */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                "Featured Jobs",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (isLoading == true) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(data?.hits ?: emptyList()) { job: JobSearch ->
                        JobCard(
                            job = job,
                            onClick = { navController.navigate("job_description/${job.id}") },
                            onApplyClick = { navController.navigate("job_application/${job.id}") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomMenuContent(viewModel: HomeScreenViewModel) {
    var selectedOption by remember { mutableStateOf("Remote") }
    var country by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Filter:", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Workplace", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Column {
            listOf("Remote", "On-site", "Hybrid").forEach { option ->
                RadioButtonWithText(
                    text = option,
                    selected = selectedOption == option,
                    onSelect = { selectedOption = option }
                )
            }
        }

        DividerSection()

        Text("Country:", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = country,
            onValueChange = { country = it },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search for a country") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            singleLine = true
        )

        DividerSection()

        Text("City:", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search for a city") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            singleLine = true
        )

        DividerSection()

        ElevatedButton(
            onClick = {
                val query = buildString {
                    if (selectedOption.isNotEmpty()) append(selectedOption)
                    if (country.isNotEmpty()) append(" $country")
                    if (city.isNotEmpty()) append(" $city")
                }.trim()
                viewModel.getJobsForCard(query)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Perpi, contentColor = Color.White)
        ) {
            Text("Filter")
        }
    }
}

@Composable
fun DividerSection() {
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider(color = Perpi, thickness = 1.dp, modifier = Modifier.padding(horizontal = 32.dp))
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun WelcomeSection(userName: String, screenWidth: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenWidth * 0.05f, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Welcome, $userName!", color = Perpi, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Let's search for your job", color = Color.Gray, fontSize = 16.sp)
        }
    }
}

@Composable
fun SearchBar(screenWidth: Dp, onSearchChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenWidth * 0.05f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                if (it.isEmpty()) onSearchChange(it)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.weight(0.75f),
            placeholder = { Text("Search a job") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            singleLine = true
        )

        IconButton(onClick = { onSearchChange(text) }) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Perpi, modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun IndeterminateCircularIndicator(viewModel: HomeScreenViewModel) {
    if (!viewModel.isLoading) return

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = Perpi
        )
    }
}