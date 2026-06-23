package com.example.martapolishchuk_comp304_midterm.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304_midterm.data.Car

// 301432299 - Marta Polishchuk

// reusable card to use in the Lazy Column inventory list
@Composable fun carListItem(car: Car) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ){ // each card needs to hold car details + a delete button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) { // car details
                Text(text = car.makeAndModel,
                    style = MaterialTheme.typography.titleMedium)
                Text(text = car.sellerName,
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = car.vehicleType,
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = car.manufacturingYear.toString(),
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = car.sellingPrice.toString(),
                    style = MaterialTheme.typography.bodyMedium)
            }

            // delete button
            Button(onClick = {
                carInventoryDisplayViewModel.deleteCar(car)
            }) {
                Text(text = "Delete")
            }

        }// row 


    }//card



} // composable end