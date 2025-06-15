package com.sa7.jobfiy.ui.screens

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
import androidx.compose.ui.window.Dialog
import java.util.regex.Pattern

@Composable
fun ApplicationForm(
    onDismiss: () -> Unit,
    onSubmit: (ApplicationData) -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var coverLetter by remember { mutableStateOf("") }
    
    var fullNameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }
    var experienceError by remember { mutableStateOf("") }
    var educationError by remember { mutableStateOf("") }
    var coverLetterError by remember { mutableStateOf("") }

    val emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    val phonePattern = Pattern.compile("^\\+?[0-9]{10,13}$")

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Job Application",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = fullName,
                    onValueChange = { 
                        fullName = it
                        fullNameError = if (it.length < 3) "Name must be at least 3 characters" else ""
                    },
                    label = { Text("Full Name") },
                    isError = fullNameError.isNotEmpty(),
                    supportingText = { if (fullNameError.isNotEmpty()) Text(fullNameError) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { 
                        email = it
                        emailError = if (!emailPattern.matcher(it).matches()) "Invalid email format" else ""
                    },
                    label = { Text("Email") },
                    isError = emailError.isNotEmpty(),
                    supportingText = { if (emailError.isNotEmpty()) Text(emailError) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { 
                        phone = it
                        phoneError = if (!phonePattern.matcher(it).matches()) "Invalid phone number" else ""
                    },
                    label = { Text("Phone Number") },
                    isError = phoneError.isNotEmpty(),
                    supportingText = { if (phoneError.isNotEmpty()) Text(phoneError) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = experience,
                    onValueChange = { 
                        experience = it
                        experienceError = if (it.isEmpty()) "Please enter your experience" else ""
                    },
                    label = { Text("Years of Experience") },
                    isError = experienceError.isNotEmpty(),
                    supportingText = { if (experienceError.isNotEmpty()) Text(experienceError) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = education,
                    onValueChange = { 
                        education = it
                        educationError = if (it.isEmpty()) "Please enter your education" else ""
                    },
                    label = { Text("Education") },
                    isError = educationError.isNotEmpty(),
                    supportingText = { if (educationError.isNotEmpty()) Text(educationError) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = coverLetter,
                    onValueChange = { 
                        coverLetter = it
                        coverLetterError = if (it.length < 50) "Cover letter must be at least 50 characters" else ""
                    },
                    label = { Text("Cover Letter") },
                    isError = coverLetterError.isNotEmpty(),
                    supportingText = { if (coverLetterError.isNotEmpty()) Text(coverLetterError) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(vertical = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Cancel")
                    }
                    
                    Button(
                        onClick = {
                            if (fullNameError.isEmpty() && emailError.isEmpty() && 
                                phoneError.isEmpty() && experienceError.isEmpty() && 
                                educationError.isEmpty() && coverLetterError.isEmpty()) {
                                onSubmit(
                                    ApplicationData(
                                        fullName = fullName,
                                        email = email,
                                        phone = phone,
                                        experience = experience,
                                        education = education,
                                        coverLetter = coverLetter
                                    )
                                )
                            }
                        }
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}

data class ApplicationData(
    val fullName: String,
    val email: String,
    val phone: String,
    val experience: String,
    val education: String,
    val coverLetter: String
) 