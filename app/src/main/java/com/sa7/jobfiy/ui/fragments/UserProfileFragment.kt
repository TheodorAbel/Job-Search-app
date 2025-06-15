package com.sa7.jobfiy.ui.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun UserProfileFragment(
    onLogout: () -> Unit,
    firstName: String,
    onUpdateProfile: (UserProfile) -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = education,
            onValueChange = { education = it },
            label = { Text("Education") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = experience,
            onValueChange = { experience = it },
            label = { Text("Experience") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = skills,
            onValueChange = { skills = it },
            label = { Text("Skills") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Bio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                onUpdateProfile(
                    UserProfile(
                        fullName = fullName,
                        email = email,
                        phone = phone,
                        education = education,
                        experience = experience,
                        skills = skills,
                        bio = bio
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Update Profile")
        }

        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Logout")
        }
    }
}

data class UserProfile(
    val fullName: String,
    val email: String,
    val phone: String,
    val education: String,
    val experience: String,
    val skills: String,
    val bio: String
) 