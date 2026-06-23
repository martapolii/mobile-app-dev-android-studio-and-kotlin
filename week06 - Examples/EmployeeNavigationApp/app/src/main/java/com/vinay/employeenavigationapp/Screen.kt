package com.vinay.employeenavigationapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen (val route: String) {
    object List : Screen("employee_list")
    object Detail : Screen("employee_detail/{employeeId}") {
        fun createRoute(employeeId: Int) = "employee_detail/$employeeId"
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: EmployeeViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            EmployeeListScreen(viewModel, navController)
        }
        composable("employee_detail/{employeeId}") { backStackEntry ->
            val employeeId = backStackEntry.arguments?.getString("employeeId")?.toIntOrNull()
            employeeId?.let {
                EmployeeDetailScreen(viewModel.getEmployeeById(it))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListScreen(viewModel: EmployeeViewModel, navController: NavController) {
    val employees by viewModel.employees.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Employees") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            employees.forEach { employee ->
                Text(
                    text = "${employee.name} - ${employee.role}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.Detail.createRoute(employee.id))
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeDetailScreen(employee: Employee?) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Employee Details") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            employee?.let {
                Text("ID: ${it.id}", style = MaterialTheme.typography.titleMedium)
                Text("Name: ${it.name}", style = MaterialTheme.typography.titleMedium)
                Text("Role: ${it.role}", style = MaterialTheme.typography.titleMedium)
            } ?: Text("Employee not found")
        }
    }
}
