package com.sa7.jobfiy.authentication.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    labelValue: String,
    icon: ImageVector = Icons.Default.Email,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next
    )
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        value = "",
        onValueChange = onTextSelected,
        label = { Text(text = labelValue) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = labelValue) },
        isError = errorStatus,
        supportingText = {
            if (errorStatus) {
                Text(
                    text = errorMessage ?: "Error",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        keyboardOptions = keyboardOptions,
        singleLine = true
    )
} 