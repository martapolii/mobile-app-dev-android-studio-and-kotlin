package com.example.martapolishchuk_comp304lab2_ex1.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CommonTopBar
import com.example.martapolishchuk_comp304lab2_ex1.ui.theme.SuccessMint
import com.example.martapolishchuk_comp304lab2_ex1.ui.theme.WarningRose

// Marta Polishchuk - 301432299

@Composable
fun HomeScreen(
    careerItemList: List<CareerItem>,
    onAddClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    // added a Scaffold to each screen to hold a top app bar + column with main content. Also made it easier to place the FAB
    Scaffold(
        topBar = {
            CommonTopBar(title = "Student Career Development Hub")
        },
        // FLOATING ACTION BUTTON
        floatingActionButton = {
            // add career item button
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add career item"
                )
            }
        } // floatingActionButton

    ) { paddingValues ->
        if (careerItemList.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("no career items yet. tap the add button to create one.")
            }
        } else {
            // lazycolumn is in the rubric for showing the list of career items
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ){
                items(
                    items = careerItemList,
                    key = { careerItem -> careerItem.id }
                ) { careerItem ->
                    // cards display the summary info for each career item on the home screen
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onItemClick(careerItem.id) },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.75f)
                        )
                    ) { // each field stacked on top of the other in a column
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = careerItem.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "category: ${careerItem.category}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "status: ${careerItem.status}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "progress: ${careerItem.progressPercentage}%",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            // completion indicator is one of the details called out in the instructions
                            val statusColor = if (careerItem.completionIndicator) SuccessMint
                            else WarningRose
                            Text(
                                text = if (careerItem.completionIndicator) "completion: completed" else "completion: still in progress",
                                style = MaterialTheme.typography.labelMedium,
                                color = statusColor
                            )
                        }
                    }
                } // items
            } // lazyColumn
        }
    } // Scaffold
} // home activity
