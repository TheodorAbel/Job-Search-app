package com.sa7.jobfiy.authentication.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonComponent(
    value: String,
    onButtonClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
} 