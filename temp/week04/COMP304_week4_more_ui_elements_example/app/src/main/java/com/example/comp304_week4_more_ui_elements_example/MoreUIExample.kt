package com.example.comp304_week4_more_ui_elements_example
import com.example.comp304_week4_more_ui_elements_example.ui.theme.COMP304_week4_more_ui_elements_exampleTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MoreUIExample(modifier: Modifier = Modifier) {

    var programOptions = arrayOf(
        "Software Engineering",
        "Artificial Intelligence",
        "Mobile App Dev",
        "Game Programming")
    var selectedProgramIndex by rememberSaveable { mutableStateOf(0) }
    var showProgramMenu by rememberSaveable { mutableStateOf(false)}

    var isFTStudent by rememberSaveable { mutableStateOf(false)}

    var residentialOptions = arrayOf("International", "Domestic", "Native")
    var selectedResidency by rememberSaveable { mutableStateOf(residentialOptions[0])}

    var fundingOptions = arrayOf("OSAP", "Bursar", "Scholarship")
    var selectedFundingOptions = rememberSaveable { mutableStateListOf(true, false, false) }

    var showDialog by rememberSaveable { mutableStateOf(false) }

    var dateMills by rememberSaveable {mutableStateOf<Long?>(value = null) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showProgramMenu = true //open the menu
                }
            ) {
                Text("Program : ${programOptions[selectedProgramIndex]}")
            }//OutlinedButton

            DropdownMenu(
                expanded = showProgramMenu,
                onDismissRequest = {
                    showProgramMenu = false //close the menu
                }
            ) {
                programOptions.forEachIndexed { index, element ->

                    DropdownMenuItem(
                        text = { Text(element) },
                        onClick = {
                            //perform some operation
                            selectedProgramIndex = index

                            //close the menu
                            showProgramMenu = false
                        }
                    )//DropdownMenuItem

                }//forEachIndexed
            }//DropdownMenu
        }//Box

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Fulltime Student?")

            Switch(
                checked = isFTStudent,
                onCheckedChange = { isFTStudent = it }
            )//Switch
        }//Row

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Select residential status : ",
            style = MaterialTheme.typography.titleMedium
        )

        residentialOptions.forEach { element ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedResidency == element,
                    onClick = { selectedResidency = element }
                )//RadioButton
                Text(element)
            }
        }//forEach

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Select funding options : ",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            fundingOptions.forEachIndexed { index, element ->
                Row(
//                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedFundingOptions[index],
                        onCheckedChange = { selectedFundingOptions[index] = it }
                    )//Checkbox
                    Text(element)
                }
            }//forEach
        }//Row

        DatePickerOutlinedField(
            label = "Registration Date",
            selectedDateMills = dateMills,
            onDateSelected = {  dateMills = it }
        )//DatePickerOutlinedField

        FilledTonalButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                //show the dialog with info
                showDialog = true
            }
        ) {
            Text("Show info")
        }//FilledTonalButton

        if(showDialog){
            AlertDialog(
                onDismissRequest = {
                    //close the dialog
                    showDialog = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            //save the info; perform operations
                            showDialog = false
                        }
                    ) {
                        Text("Okay")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                },
                title = { Text("Registration Info") },
                text = {
                    Column {
                        Text("Program : ${programOptions[selectedProgramIndex]}")
                        Text("Fulltime  : ${if (isFTStudent) "yes" else "no"}")
                        Text("Residency : ${selectedResidency}")
                        Text("Funding : ${fundingOptions.filterIndexed { index, _ -> selectedFundingOptions[index] }.joinToString(", ")}")
                        Text("Registration Date: ${dateMills?.let{formatDateUTCStr(it)}.orEmpty()}")
                    }//Column
                }
            )//AlertDialog
        }//if

    }//Column
}//MoreUIExample



@Preview(showBackground = true)
@Composable
fun MoreUIExamplePreview() {
    COMP304_week4_more_ui_elements_exampleTheme {
        MoreUIExample()
    }
}
