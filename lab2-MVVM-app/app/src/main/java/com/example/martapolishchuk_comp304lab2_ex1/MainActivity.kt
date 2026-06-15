package com.example.martapolishchuk_comp304lab2_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304lab2_ex1.ui.theme.MartaPolishchuk_COMP304Lab2_Ex1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = CareerItemRepositoryImpl()
        val factory = CareerItemViewModelFactory(repository) // passing repository object as constructor confirms dependency injection was implemented
        val viewModel = ViewModelProvider(this, factory).get(CareerItemViewModel::class.java)

        setContent {
            MartaPolishchuk_COMP304Lab2_Ex1Theme {
                HomeScreen(viewModel = viewModel())

                }
            }
        }
    }

@Composable
fun HomeScreen(viewModel: CareerItemViewModel){}




