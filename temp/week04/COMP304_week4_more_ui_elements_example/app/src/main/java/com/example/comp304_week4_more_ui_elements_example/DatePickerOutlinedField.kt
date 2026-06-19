package com.example.comp304_week4_more_ui_elements_example

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerOutlinedField(
    label: String,
    selectedDateMills : Long?, //nullable
    onDateSelected : (Long) -> Unit,
    modifier : Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }

    val dateText = remember(selectedDateMills) {
        selectedDateMills?.let {
            //convert the date in required format
            formatDateUTCStr(it)
        }.orEmpty()
    }

    OutlinedTextField(
        value = dateText,
        onValueChange = { /* nothing */ },
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Select date",
                modifier = Modifier.clickable{
                    //show date picker
                    showDatePicker = true
                }
            )},
        modifier = modifier.fillMaxWidth()
    )//OutlinedTextField

    if (showDatePicker){
        //configure DatePicker dialog

        val datePickerState = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Picker
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        //save the selected date using date picker state
                        datePickerState.selectedDateMillis?.let{
                            onDateSelected(it)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("Select")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }//DatePickerDialog
    }//if

}//DatePickerOutlinedField

//function for date conversion
fun formatDateUTCStr(mills: Long) : String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(Date(mills))
}