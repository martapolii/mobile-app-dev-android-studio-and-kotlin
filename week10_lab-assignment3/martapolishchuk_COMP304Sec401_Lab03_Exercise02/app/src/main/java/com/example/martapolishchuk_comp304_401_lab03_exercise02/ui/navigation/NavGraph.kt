package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.navigation

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens.AddMovieScreen
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens.EditMovieScreen
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens.FavoritesScreen
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens.HomeScreen

// set up navigation routes

@Composable
fun AppNavigation(navController: NavHostController, windowSizeClass: WindowSizeClass) {
    val isExpanded = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    // Start on home_detail if expanded, otherwise home
    val startDestination = if (isExpanded) "home_detail" else "home"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen(navController, windowSizeClass = windowSizeClass)
        }
        composable("home_detail") {
            HomeScreen(navController, windowSizeClass = windowSizeClass)
        }
        composable("add") {
            AddMovieScreen(navController)
        }
        composable(
            route = "edit/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            EditMovieScreen(
                movieId = backStackEntry.arguments?.getInt("movieId"),
                navController = navController
            )
        }
        composable("favorites") {
            FavoritesScreen(navController, windowSizeClass = windowSizeClass)
        }
    }
}