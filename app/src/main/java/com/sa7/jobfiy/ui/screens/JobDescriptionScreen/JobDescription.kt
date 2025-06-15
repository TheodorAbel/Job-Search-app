package com.sa7.jobfiy.ui.screens.JobDescriptionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sa7.jobfiy.ui.screens.HomeScreen.HomeScreenViewModel
import com.sa7.jobfiy.api.JobSearch
import androidx.compose.runtime.livedata.observeAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailPage(
    navController: NavController,
    viewModel: HomeScreenViewModel,
    jobId: String?
) {
    val jobs = viewModel.data.observeAsState().value?.hits
    val job = jobs?.find { it.id == jobId }
    val isBookmarked = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Job Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { isBookmarked.value = !isBookmarked.value }) {
                        Icon(
                            if (isBookmarked.value) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isBookmarked.value) "Remove bookmark" else "Add bookmark"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Job Title and Company
            Text(
                text = job?.title ?: "Job Title",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = job?.company_name ?: "Company Name",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            // Location
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = job?.location ?: "Location",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Salary
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Salary",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${job?.salary?.min ?: 0} - ${job?.salary?.max ?: 0} ${job?.salary?.type ?: "ETB"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            // Job Description
            Text(
                text = "Job Description",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "We are looking for a ${job?.title} to join our team. The ideal candidate should have strong technical skills and experience in the field.",
                style = MaterialTheme.typography.bodyLarge
            )

            // Requirements
            Text(
                text = "Requirements",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "• Strong technical skills\n• Experience in the field\n• Good communication skills\n• Team player",
                style = MaterialTheme.typography.bodyLarge
            )

            // Apply Button
            Button(
                onClick = { /* TODO: Implement apply functionality */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Apply Now",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}