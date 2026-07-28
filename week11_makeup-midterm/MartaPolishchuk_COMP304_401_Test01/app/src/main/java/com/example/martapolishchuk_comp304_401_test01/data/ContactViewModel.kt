package com.example.martapolishchuk_comp304_401_test01.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ContactViewModel: ViewModel() {
    private val_contacts = mutableStateListOf<Contact>()
    val contacts:List<Contact>= _contacts

    fun addContact(contact:Contact) {
        _contacts.add(contact)
    }
}