package com.example.martapolishchuk_comp304lab2_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepositoryImpl
import com.example.martapolishchuk_comp304lab2_ex1.data.careerItemList
import com.example.martapolishchuk_comp304lab2_ex1.screens.create.CreateCareerItemScreen
import com.example.martapolishchuk_comp304lab2_ex1.screens.create.CreateCareerItemViewModel
import com.example.martapolishchuk_comp304lab2_ex1.screens.create.CreateCareerItemViewModelFactory
import com.example.martapolishchuk_comp304lab2_ex1.screens.edit.EditCareerItemScreen
import com.example.martapolishchuk_comp304lab2_ex1.screens.home.HomeScreen
import com.example.martapolishchuk_comp304lab2_ex1.ui.theme.MartaPolishchuk_COMP304Lab2_Ex1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = CareerItemRepositoryImpl()
        val factory =
            CreateCareerItemViewModelFactory(repository) // passing repository object as constructor confirms dependency injection was implemented
        val viewModel = ViewModelProvider(this, factory).get(CreateCareerItemViewModel::class.java)

        setContent {
            MartaPolishchuk_COMP304Lab2_Ex1Theme {
                App(viewModel = viewModel)
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(viewModel: CreateCareerItemViewModel) {

    // use variables to store states that will be passed into the 3 activities
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var selectedCareerItemIndex by remember { mutableStateOf(-1)}

    when (currentScreen) {

        Screen.HOME -> HomeScreen(
            careerItemList = careerItemList,
            onAddClick = { currentScreen = Screen.CREATE },
            onItemClick = { index ->
                selectedCareerItemIndex = index
                currentScreen = Screen.EDIT
            }
        )

        Screen.CREATE -> CreateCareerItemScreen(
            onBackClick = { currentScreen = Screen.HOME },
            onSave = { newCareerItem ->
                careerItemList.add(newCareerItem)
                currentScreen = Screen.HOME
            },
            viewModel = viewModel
        )

        Screen.EDIT -> {
            if (selectedCareerItemIndex >= 0) {
                EditCareerItemScreen(
                    event = careerItemList[selectedCareerItemIndex],
                    onSave = { updatedCareerItem ->
                        currentScreen = Screen.HOME
                    }
                )
            }
        }
    }
}




