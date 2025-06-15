package com.sa7.jobfiy.ui.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sa7.jobfiy.api.JobSearch
import com.sa7.jobfiy.api.Salary
import com.sa7.jobfiy.ui.commonUi.JobCard
import com.sa7.jobfiy.ui.theme.Perpi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobSearchFragment(
    navController: NavController,
    onJobClick: (String) -> Unit = { jobId -> 
        navController.navigate("job_description/$jobId")
    }
) {
    var showFilter by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var selectedEducation by remember { mutableStateOf<String?>(null) }
    var selectedJobType by remember { mutableStateOf<String?>(null) }

    // Sample job data - replace with actual API call
    val jobs = remember {
        mutableStateListOf(
            JobSearch(
                id = "1",
                title = "Senior Android Developer",
                company_name = "Tech Corp",
                location = "Addis Ababa",
                locality = "Bole",
                formatted_relative_time = "2 days ago",
                salary = Salary(min = 50000.0, max = 80000.0, type = "ETB"),
                link = "https://example.com/job1",
                pub_date_ts_milli = System.currentTimeMillis()
            ),
            JobSearch(
                id = "2",
                title = "UI/UX Designer",
                company_name = "Design Studio",
                location = "Addis Ababa",
                locality = "Sarbet",
                formatted_relative_time = "1 week ago",
                salary = Salary(min = 40000.0, max = 60000.0, type = "ETB"),
                link = "https://example.com/job2",
                pub_date_ts_milli = System.currentTimeMillis()
            )
        )
    }

    val filteredJobs = remember(jobs, searchQuery, selectedCategory, selectedEducation, selectedJobType) {
        jobs.filter { job ->
            val matchesSearch = job.title.contains(searchQuery, ignoreCase = true) ||
                    job.company_name.contains(searchQuery, ignoreCase = true)
            val matchesCategory = selectedCategory == null || job.title.contains(selectedCategory!!, ignoreCase = true)
            val matchesEducation = selectedEducation == null // Add education field to JobSearch model
            val matchesJobType = selectedJobType == null // Add job type field to JobSearch model
            matchesSearch && matchesCategory && matchesEducation && matchesJobType
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Search jobs...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                IconButton(onClick = { showFilter = !showFilter }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Filter",
                        tint = if (showFilter) Perpi else MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            singleLine = true
        )

        // Filter Section
        if (showFilter) {
            FilterSection(
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                selectedEducation = selectedEducation,
                onEducationSelected = { selectedEducation = it },
                selectedJobType = selectedJobType,
                onJobTypeSelected = { selectedJobType = it }
            )
        }

        // Job List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredJobs) { job ->
                JobCard(
                    job = job,
                    onClick = { onJobClick(job.id) },
                    onApplyClick = {
                        navController.navigate("job_application/${job.id}")
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSection(
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit,
    selectedEducation: String?,
    onEducationSelected: (String?) -> Unit,
    selectedJobType: String?,
    onJobTypeSelected: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            "Categories",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = "IT",
                selected = selectedCategory == "IT",
                onClick = { onCategorySelected(if (selectedCategory == "IT") null else "IT") }
            )
            FilterChip(
                text = "Finance",
                selected = selectedCategory == "Finance",
                onClick = { onCategorySelected(if (selectedCategory == "Finance") null else "Finance") }
            )
            FilterChip(
                text = "Marketing",
                selected = selectedCategory == "Marketing",
                onClick = { onCategorySelected(if (selectedCategory == "Marketing") null else "Marketing") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Education Level",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = "Bachelor's",
                selected = selectedEducation == "Bachelor's",
                onClick = { onEducationSelected(if (selectedEducation == "Bachelor's") null else "Bachelor's") }
            )
            FilterChip(
                text = "Master's",
                selected = selectedEducation == "Master's",
                onClick = { onEducationSelected(if (selectedEducation == "Master's") null else "Master's") }
            )
            FilterChip(
                text = "PhD",
                selected = selectedEducation == "PhD",
                onClick = { onEducationSelected(if (selectedEducation == "PhD") null else "PhD") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Job Type",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = "Full-time",
                selected = selectedJobType == "Full-time",
                onClick = { onJobTypeSelected(if (selectedJobType == "Full-time") null else "Full-time") }
            )
            FilterChip(
                text = "Part-time",
                selected = selectedJobType == "Part-time",
                onClick = { onJobTypeSelected(if (selectedJobType == "Part-time") null else "Part-time") }
            )
            FilterChip(
                text = "Contract",
                selected = selectedJobType == "Contract",
                onClick = { onJobTypeSelected(if (selectedJobType == "Contract") null else "Contract") }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(text) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Perpi,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
} 