package com.example.martapolishchuk_comp304_midterm.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304_midterm.data.CarFormState
import com.example.martapolishchuk_comp304_midterm.data.vehicleTypeOptions

// 301432299 - Marta Polishchuk

// form for filling out new Car details
// (I realized we don't need an edit screen for this App - I was going to re-use the form for the create new car and edit car screen - for this app im not reusing this form, but i just left it as a reusable compoennt anyways)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarForm(
    formState: CarFormState,
    validationMessage: String?,
    readOnly: Boolean = false,
    onMakeAndModelChange: (String) -> Unit,
    onSellerNameChange: (String) -> Unit,
    onVehicleTypeChange: (String) -> Unit,
    onManufacturingYearChange: (String) -> Unit,
    onSellingPriceChange: (String) -> Unit,
    onAddCarClick: () -> Unit,
    modifier: Modifier = Modifier

    ){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    { Text(
            text = "Add New Car Form",
            style = MaterialTheme.typography.titleLarge
        )

        // make and model
        OutlinedTextField(
            value = formState.makeAndModel,
            onValueChange = onMakeAndModelChange,
            label = { Text("Make and Model") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly
        )
        // seller name
        OutlinedTextField(
            value = formState.sellerName,
            onValueChange = onSellerNameChange,
            label = { Text("Seller Name") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly
        )
        // segmented button
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            vehicleTypeOptions.forEachIndexed { index, option ->
                SegmentedButton(
                    selected = formState.vehicleType == option,
                    onClick = { onVehicleTypeChange(option) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = vehicleTypeOptions.size,
                    ),
                    enabled = !readOnly,
                    label = { Text(text = option) }
                )//seg. button
            }
        }// seg. button row

        // manufacturing year
        OutlinedTextField(
            value = formState.manufacturingYear.toString(),
            onValueChange = onManufacturingYearChange,
            label = { Text("Manufacturing Year") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly
        )

        // selling price
        OutlinedTextField(
            value = formState.sellingPrice.toString(),
            onValueChange = onSellingPriceChange,
            label = { Text("Selling Price") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly
        )

        // 'Add Car' button
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Card(
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "Save Car",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Button(
                    onClick = onAddCarClick,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Save Car")
                }
            }
        }

        if (validationMessage != null) {
            Text(
                text = validationMessage,
                color = MaterialTheme.colorScheme.error
            )
        }

        // ***TO-DO IF TIME ALLOWS***
        // snackbar (from https://developer.android.com/develop/ui/compose/components/snackbar)
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }


}// column

}
