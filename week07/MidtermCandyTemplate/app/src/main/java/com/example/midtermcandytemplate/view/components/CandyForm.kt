package com.example.midtermcandytemplate.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.midtermcandytemplate.R
import com.example.midtermcandytemplate.model.CandyFormState
import com.example.midtermcandytemplate.model.candyFlavorProfileOptions
import com.example.midtermcandytemplate.model.candyTypeOptions

/**
 * Reusable form for both create and edit flows.
 *
 * The comments stay directly above each major control so you can quickly copy the
 * exact Jetpack Compose element you need during the midterm.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandyForm(
    formState: CandyFormState,
    validationMessage: String?,
    readOnly: Boolean,
    primaryButtonText: String,
    showPrimaryButton: Boolean,
    onNameChange: (String) -> Unit,
    onTypeChange: (String) -> Unit,
    onSugarFreeChange: (Boolean) -> Unit,
    onFlavorProfileChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onFavoriteToggle: () -> Unit,
    onPrimaryButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Text
        Text(
            text = "Reusable candy form template",
            style = MaterialTheme.typography.titleLarge
        )

        // Text
        Text(
            text = "This screen is intentionally packed with study-ready controls.",
            style = MaterialTheme.typography.bodyMedium
        )

        // Box
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Image
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Placeholder candy image",
                        modifier = Modifier.size(96.dp)
                    )

                    // Text
                    Text(
                        text = "Replace this placeholder image with your own candy image later.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Image button
            IconButton(
                onClick = onFavoriteToggle,
                enabled = !readOnly,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (formState.isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    },
                    contentDescription = "Toggle favorite candy"
                )
            }
        }

        // Text field
        StudyTextField(
            label = "Candy name",
            value = formState.name,
            onValueChange = onNameChange,
            readOnly = readOnly
        )

        // Text
        Text(
            text = "Candy type",
            style = MaterialTheme.typography.titleMedium
        )

        // Radio buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            candyTypeOptions.forEach { option ->
                // Row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Radio button
                    RadioButton(
                        selected = formState.type == option,
                        onClick = { onTypeChange(option) },
                        enabled = !readOnly
                    )

                    // Text
                    Text(text = option)
                }
            }
        }

        // Row
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox
            Checkbox(
                checked = formState.isSugarFree,
                onCheckedChange = onSugarFreeChange,
                enabled = !readOnly
            )

            // Text
            Text(text = "Sugar-free option")
        }

        // Text
        Text(
            text = "Flavor profile",
            style = MaterialTheme.typography.titleMedium
        )

        // Segmented button
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            candyFlavorProfileOptions.forEachIndexed { index, option ->
                SegmentedButton(
                    selected = formState.flavorProfile == option,
                    onClick = { onFlavorProfileChange(option) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = candyFlavorProfileOptions.size
                    ),
                    enabled = !readOnly,
                    label = {
                        Text(option)
                    }
                )
            }
        }

        // Text field
        StudyTextField(
            label = "Candy description",
            value = formState.description,
            onValueChange = onDescriptionChange,
            readOnly = readOnly,
            singleLine = false,
            minLines = 3
        )

        if (validationMessage != null) {
            // Text
            Text(
                text = validationMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (showPrimaryButton) {
            // Button
            Button(
                onClick = onPrimaryButtonClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(primaryButtonText)
            }
        }

        // Text
        Text(
            text = "Live summary",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Text
                Text(text = "Name: ${formState.name.ifBlank { "No name yet" }}")

                // Text
                Text(text = "Type: ${formState.type}")

                // Text
                Text(text = "Sugar-free: ${if (formState.isSugarFree) "Yes" else "No"}")

                // Text
                Text(text = "Flavor profile: ${formState.flavorProfile}")

                // Text
                Text(text = "Favorite: ${if (formState.isFavorite) "Yes" else "No"}")

                Spacer(modifier = Modifier.height(4.dp))

                // Text
                Text(
                    text = "Description: ${formState.description.ifBlank { "No description yet" }}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

/**
 * Reusable text field wrapper for quick copy/paste.
 */
@Composable
fun StudyTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        modifier = Modifier.fillMaxWidth(),
        readOnly = readOnly,
        singleLine = singleLine,
        minLines = minLines
    )
}
