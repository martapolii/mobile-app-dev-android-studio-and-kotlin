package com.example.martapolishchuk_comp304lab2_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepositoryImpl
import com.example.martapolishchuk_comp304lab2_ex1.screens.create.CreateCareerItemScreen
import com.example.martapolishchuk_comp304lab2_ex1.screens.create.CreateCareerItemViewModel
import com.example.martapolishchuk_comp304lab2_ex1.screens.create.CreateCareerItemViewModelFactory
import com.example.martapolishchuk_comp304lab2_ex1.screens.edit.EditCareerItemScreen
import com.example.martapolishchuk_comp304lab2_ex1.screens.edit.EditCareerItemViewModel
import com.example.martapolishchuk_comp304lab2_ex1.screens.edit.EditCareerItemViewModelFactory
import com.example.martapolishchuk_comp304lab2_ex1.screens.home.HomeScreen
import com.example.martapolishchuk_comp304lab2_ex1.screens.home.HomeViewModel
import com.example.martapolishchuk_comp304lab2_ex1.screens.home.HomeViewModelFactory
import com.example.martapolishchuk_comp304lab2_ex1.ui.theme.MartaPolishchuk_COMP304Lab2_Ex1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // this is the dependency injection part from the mvvm example
        val repository = CareerItemRepositoryImpl()
        val homeFactory = HomeViewModelFactory(repository)
        val createFactory =
            CreateCareerItemViewModelFactory(repository) // passing repository object as constructor confirms dependency injection was implemented
        val editFactory = EditCareerItemViewModelFactory(repository)

        val homeViewModel = ViewModelProvider(this, homeFactory).get(HomeViewModel::class.java)
        val createViewModel = ViewModelProvider(this, createFactory).get(CreateCareerItemViewModel::class.java)
        val editViewModel = ViewModelProvider(this, editFactory).get(EditCareerItemViewModel::class.java)

        setContent {
            MartaPolishchuk_COMP304Lab2_Ex1Theme {
                App(
                    homeViewModel = homeViewModel,
                    createViewModel = createViewModel,
                    editViewModel = editViewModel
                )
            }
        }
    }
}

enum class Screen{
    HOME,
    CREATE,
    EDIT
}

// APP ---------------------------------------------------------------------------------------------
// made this to control switching between the different screens and to pass along the variables that store states
@Composable
fun App(
    homeViewModel: HomeViewModel,
    createViewModel: CreateCareerItemViewModel,
    editViewModel: EditCareerItemViewModel
) {

    // use variables to store states that will be passed into the 3 screens
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var selectedCareerItemId by remember { mutableStateOf(-1) }
    val careerItemList by homeViewModel.careerItems.collectAsState()

    when (currentScreen) {

        Screen.HOME -> HomeScreen(
            careerItemList = careerItemList,
            onAddClick = { currentScreen = Screen.CREATE },
            onItemClick = { itemId ->
                selectedCareerItemId = itemId
                currentScreen = Screen.EDIT
            }
        )

        Screen.CREATE -> CreateCareerItemScreen(
            onBackClick = { currentScreen = Screen.HOME },
            viewModel = createViewModel
        )

        Screen.EDIT -> {
            val selectedCareerItem = editViewModel.getCareerItemById(selectedCareerItemId)

            if (selectedCareerItem != null) {
                EditCareerItemScreen(
                    careerItem = selectedCareerItem,
                    onBackClick = { currentScreen = Screen.HOME },
                    viewModel = editViewModel
                )
            } else {
                LaunchedEffect(selectedCareerItemId) {
                    currentScreen = Screen.HOME
                }
            }
        }
    }
}
