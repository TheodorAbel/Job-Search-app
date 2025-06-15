package com.sa7.jobfiy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    currentTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit
) {
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
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Settings",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Theme Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dark Theme",
                        fontSize = 16.sp
                    )
                    Switch(
                        checked = currentTheme,
                        onCheckedChange = onThemeChange
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                // Language Selection
                Text(
                    text = "Language",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LanguageButton(
                        text = "English",
                        isSelected = currentLanguage == "English",
                        onClick = { onLanguageChange("English") }
                    )
                    LanguageButton(
                        text = "አማርኛ",
                        isSelected = currentLanguage == "አማርኛ",
                        onClick = { onLanguageChange("አማርኛ") }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun LanguageButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
        )
    ) {
        Text(text)
    }
} 