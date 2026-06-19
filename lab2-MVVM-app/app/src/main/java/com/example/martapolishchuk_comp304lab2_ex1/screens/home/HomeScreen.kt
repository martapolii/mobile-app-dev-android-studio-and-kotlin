package com.example.martapolishchuk_comp304lab2_ex1.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.careerItemList
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CommonTopBar


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(
//    viewModel: HomeViewModel,
//    careerItemList: List<CareerItem>,
//    onAddClick: () -> Unit,
//    onItemClick: (Int) -> Unit
//){
//
//    // used a lazy column to display multiple career items:
//    LazyColumn(
//        modifier = Modifier.weight(1f)
//    ) {
//        items(careerItemList.size) { index ->
//            val careerItem = careerItemList[index].
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            ) {
//                Text("Title: ${careerItem.title}")
//                Text("Category: ${careerItem.category}")
//                Text("Progress Status: ${careerItem.progressStatus}")
//                Text("Completion Indicator: ${careerItem.completionIndicator}")
//            }
//
//        } // items
//    } // lazy column
//}



/* HOME ACTIVITY -----------------------------------------------------------------------------------
- display events in Lazy Column
- each event: name, location, date, indication whether upcoming or completed
- FAB: Add Event

JetPack Components:
- Lazy column for displaying list
- Card for each event
- FAB: add events

Event data class
 */

@OptIn(ExperimentalMaterial3Api::class)
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
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Text("+")
            }
        } // floatingActionButton

    ) { paddingValues ->
        // LAZY COLUMN
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ){
            itemsIndexed(careerItemList) { index, careerItem ->
                // CARDS to display details of each career item
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onItemClick(index) },
                    shape = RoundedCornerShape(16.dp)
                ) { // each field stacked on top of the other in a column
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Title: ${careerItem.title}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text("Category: ${careerItem.category}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text("Progress Status: ${careerItem.progressStatus}",
                            style = MaterialTheme.typography.bodyMedium)

                        // created a value to store the colour of the status toggle
                        val statusColor = if (careerItem.completionIndicator) Color(0xFF009688)
                        else Color(0xFFD05B52)
                        Text(
                            text = if (careerItem.completionIndicator) "Status: Completed" else "Status: Incompleted",
                            style = MaterialTheme.typography.labelMedium,
                            color = statusColor
                        )
                    }
                }
            } //itemsIndexed

        } // lazyColumn
    } // Scaffold
} // home activity