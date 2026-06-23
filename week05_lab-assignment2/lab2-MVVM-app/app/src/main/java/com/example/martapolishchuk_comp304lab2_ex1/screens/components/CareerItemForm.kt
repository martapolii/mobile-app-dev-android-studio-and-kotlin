package com.example.martapolishchuk_comp304lab2_ex1.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// Marta Polishchuk - 301432299

// reusable form composable keeps the create/edit screens consistent
@Composable
fun CareerItemForm(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    category: String,
    categoryOptions: List<String>,
    onCategoryChange: (String) -> Unit,
    startDate: String,
    onStartDateChange: (String) -> Unit,
    targetCompletionDate: String,
    onTargetCompletionDateChange: (String) -> Unit,
    status: String,
    statusOptions: List<String>,
    onStatusChange: (String) -> Unit,
    progressPercentage: String,
    onProgressPercentageChange: (String) -> Unit,
    validationMessage: String?,
    saveButtonText: String,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("title") },
            modifier = Modifier.fillMaxWidth(),
            colors = formTextFieldColors()
        )

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text("description") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            colors = formTextFieldColors()
        )

        DropdownSelector(
            label = "category",
            selectedValue = category,
            options = categoryOptions,
            onOptionSelected = onCategoryChange
        )

        OutlinedTextField(
            value = startDate,
            onValueChange = onStartDateChange,
            label = { Text("start date") },
            supportingText = { Text("use yyyy-mm-dd") },
            modifier = Modifier.fillMaxWidth(),
            colors = formTextFieldColors()
        )

        OutlinedTextField(
            value = targetCompletionDate,
            onValueChange = onTargetCompletionDateChange,
            label = { Text("target completion date") },
            supportingText = { Text("use yyyy-mm-dd") },
            modifier = Modifier.fillMaxWidth(),
            colors = formTextFieldColors()
        )

        DropdownSelector(
            label = "status",
            selectedValue = status,
            options = statusOptions,
            onOptionSelected = onStatusChange
        )

        OutlinedTextField(
            value = progressPercentage,
            onValueChange = onProgressPercentageChange,
            label = { Text("progress percentage") },
            supportingText = { Text("enter a number from 0 to 100") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = formTextFieldColors()
        )

        if (validationMessage != null) {
            Text(
                text = validationMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text(saveButtonText)
        }
    }
}

@Composable
private fun DropdownSelector(
    label: String,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(selectedValue)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun formTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = MaterialTheme.colorScheme.secondary,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    cursorColor = MaterialTheme.colorScheme.tertiary,
    unfocusedBorderColor = MaterialTheme.colorScheme.outline
)

fun validateCareerItemInput(
    title: String,
    description: String,
    startDate: String,
    targetCompletionDate: String,
    progressPercentage: String
): String? {
    // simple validation for the required fields from the instructions
    if (
        title.isBlank() ||
        description.isBlank() ||
        startDate.isBlank() ||
        targetCompletionDate.isBlank()
    ) {
        return "please fill in all required fields."
    }

    val parsedProgress = progressPercentage.toIntOrNull()
        ?: return "progress percentage needs to be a number."

    if (parsedProgress !in 0..100) {
        return "progress percentage needs to be between 0 and 100."
    }

    return null
}
