// Marta Polishchuk - 301432299



package com.example.martapolishchuk_comp304_401_test01

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304_401_test01.data.Contact

@Composable
fun AddContactScreen(
    onSave:(Contact)->Unit
){
    var contactId by remember{mutableStateOf("")}
    var name by remember {mutableStateOf("") }
    var cellPhone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contactType by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text="Add a New Contact", // page title
            style= MaterialTheme.typography.headlineMedium
        )

        // field: contact ID
        OutlinedTextField(
            value = contactId,
            onValueChange = {name = it},
            label = {Text("Contact ID")},
            modifier = Modifier.fillMaxWidth()
        )

        // field: name

        // field: cellPhone

        // field: email

        // drop-down: contact type

        // radio button: favourite

        // "ADD NEW CONTACT" button - triggers Toast with contact details





    } // column
    




} // end of addContactScreen



