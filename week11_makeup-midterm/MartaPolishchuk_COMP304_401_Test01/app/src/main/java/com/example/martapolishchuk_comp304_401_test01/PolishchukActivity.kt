// Marta Polishchuk - 301432299



package com.example.martapolishchuk_comp304_401_test01

import android.provider.MediaStore
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304_401_test01.data.Contact
import com.example.martapolishchuk_comp304_401_test01.data.contactTypeOptions
import com.example.martapolishchuk_comp304_401_test01.data.favouriteOptions
import kotlinx.coroutines.selects.select


@Composable
fun AddContactScreen(
    onSave:(Contact)->Unit
){
    // variables to store all user inputs:
    var contactId by remember{mutableStateOf("")}
    var name by remember {mutableStateOf("") }
    var cellPhone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contactType by remember { mutableStateOf(contactTypeOptions[0]) } // default will be family (bc index 0 in list - see list in data class)
    var favourite by remember{ mutableStateOf(favouriteOptions[0])} // default will be 'No'

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // form title
        Text(
            text="Add a New Contact",
            style= MaterialTheme.typography.headlineMedium
        )

        // field: contact ID
        OutlinedTextField(
            value = contactId,
            onValueChange = {contactId = it},
            label = {Text("Contact ID")},
            modifier = Modifier.fillMaxWidth()
        )

        // field: name
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("Name")},
            modifier = Modifier.fillMaxWidth()
        )

        // field: cellPhone
        OutlinedTextField(
            value = cellPhone,
            onValueChange = {cellPhone = it},
            label = {Text("Cell Phone")},
            modifier = Modifier.fillMaxWidth()
        )

        // field: email
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            modifier = Modifier.fillMaxWidth()
        )

        // drop-down: contact type
        MinimalDropdownMenu(
            selectedContactType = contactType,
            onContactTypeSelected = { contactType = it }
        )

        // radio button: favourite
        Text(
            text="Favourite?"
        )

        favouriteOptions.forEach{ option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = (favourite == option),
                    onClick = {favourite = option}
                )
                Text(text = option)
            }// row
        }//favoptions
        


        // "ADD NEW CONTACT" button - triggers Toast with contact details





    } // column





} // end of addContactScreen

// drop-down: contact type (Family, Personal, Relative) (https://developer.android.com/develop/ui/compose/components/menu?authuser=1)
@Composable
fun MinimalDropdownMenu(
    selectedContactType: String,
    onContactTypeSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.padding(16.dp)
        ){
            Button(onClick = {expanded = true}) {
                Text(selectedContactType)
            }//button

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ) {
                contactTypeOptions.forEach{ option ->
                    DropdownMenuItem(
                        text = {Text(option)},
                        onClick = {
                            onContactTypeSelected(option)
                            expanded = false
                        }
                    )
            }//dropdownmenuitem
        }//dropdownmenu
    }//box
}



