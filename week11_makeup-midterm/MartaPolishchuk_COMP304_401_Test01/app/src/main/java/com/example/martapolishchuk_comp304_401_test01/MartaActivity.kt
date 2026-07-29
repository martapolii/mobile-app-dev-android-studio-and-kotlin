// Marta Polishchuk - 301432299

// displayed when app opens
// label " My Personal Contacts" and an image button that leads to the second activity

package com.example.martapolishchuk_comp304_401_test01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.martapolishchuk_comp304_401_test01.data.Contact
import com.example.martapolishchuk_comp304_401_test01.data.ContactViewModel
import com.example.martapolishchuk_comp304_401_test01.ui.theme.MartaPolishchuk_COMP304_401_Test01Theme

class MartaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MartaPolishchuk_COMP304_401_Test01Theme {

                // using JetPack compose navigation as we covered this in week 07 and the instructions for this midterm say to use Navigation (in Dr. Vinay's midterm we didn't - I hope I understood correctly & its ok to do it this way)
                val navController = rememberNavController()
                val viewModel: ContactViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ){ // home page - imagebutton
                    composable("home") {
                        HomeScreen(
                            onNavigateToContacts = {
                                navController.navigate("contacts")
                            }
                        )
                    }
                    // view contacts screen
                    composable("contacts") {
                        ContactListScreen(
                            contacts = viewModel.contacts,
                            onAddContact = {
                                navController.navigate("add")
                            }
                        )
                    }
                    // add contacts screen
                    composable("add") {
                        AddContactScreen(
                            onSave = { contact ->
                                viewModel.addContact(contact)
                                navController.popBackStack()
                            }
                        )
                    }

                }// nav host
            }
        }
    }
}

// what users see when app first starts -> click the image button to access the app
@Composable
fun HomeScreen(
    onNavigateToContacts: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Personal Contacts",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        IconButton(
            onClick = onNavigateToContacts
        ) {
            Image(
                painter = painterResource(id = R.drawable.address_book),
                contentDescription = "Manage Contacts",
                modifier = Modifier.height(120.dp)
            )
        }
    }
}

// screen users are taken to after clicking on the image button
@Composable
fun ContactListScreen(
    contacts: List<Contact>,
    onAddContact: () -> Unit
) {
    Scaffold(

        // FAB to add a new contact
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddContact
            ) {
                Text("+")
            }
        }

    ) { padding ->

        // Lazy Column to display all contacts
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {


            items(contacts) { contact ->
                // each contact is displayed on a card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    // contact info in column
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(contact.name)
                        Text(contact.cellPhone)
                        Text(contact.email)
                        Text(contact.contactType)
                        Text("Favourite: ${contact.favourite}")
                    }

                    if (contacts.isEmpty()) {
                        Text(
                            text = "No contacts added yet",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

