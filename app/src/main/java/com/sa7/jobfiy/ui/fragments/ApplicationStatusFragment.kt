package com.sa7.jobfiy.ui.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sa7.jobfiy.ui.theme.Perpi

@Composable
fun ApplicationStatusFragment(
    applications: List<ApplicationStatus>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Application Status",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(applications) { application ->
                ApplicationStatusCard(application)
            }
        }
    }
}

@Composable
fun ApplicationStatusCard(application: ApplicationStatus) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (application.status) {
                ApplicationStatus.Status.ACCEPTED -> Color(0xFFE8F5E9)
                ApplicationStatus.Status.REJECTED -> Color(0xFFFFEBEE)
                ApplicationStatus.Status.PENDING -> Color(0xFFFFF3E0)
                ApplicationStatus.Status.INTERVIEW -> Color(0xFFE3F2FD)
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = application.jobTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = application.company,
                fontSize = 16.sp,
                color = Color.Gray
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Status: ${application.status.name}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = application.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

data class ApplicationStatus(
    val jobTitle: String,
    val company: String,
    val status: Status,
    val date: String
) {
    enum class Status {
        PENDING,
        ACCEPTED,
        REJECTED,
        INTERVIEW
    }
} 