package com.example.midtermcandytemplate.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * This composable is not linked into the running UI on purpose.
 *
 * Keep it as a study shelf of extra Material 3 widgets you might want to copy
 * into a screen during the midterm.
 */
@Composable
fun OptionalStudyComponents(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Text
        Text("Optional Material 3 components")

        // Outlined button
        OutlinedButton(
            onClick = { }
        ) {
            Text("Outlined button example")
        }

        // Elevated button
        ElevatedButton(
            onClick = { }
        ) {
            Text("Elevated button example")
        }

        // Assist chip
        AssistChip(
            onClick = { },
            label = {
                Text("Assist chip example")
            }
        )

        // Horizontal divider
        HorizontalDivider()

        // Elevated card
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Elevated card example",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
