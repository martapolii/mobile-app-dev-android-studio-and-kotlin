package com.vinay.employeenavigationapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EmployeeViewModel  : ViewModel() {

    private val _employees = MutableStateFlow(
        listOf(
            Employee(1, "Alice", "Manager"),
            Employee(2, "Bob", "Developer"),
            Employee(3, "Charlie", "Director")
        )
    )
    val employees: StateFlow<List<Employee>> = _employees

    fun getEmployeeById(id: Int): Employee? {
        return _employees.value.find { it.id == id }
    }
}