package com.olelopez.gymreport.ui.navigation

import ExerciseSetListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.olelopez.gymreport.ui.ExerciseViewModel
import com.olelopez.gymreport.ui.composables.InsertExerciseAndSetScreen

@Composable
fun AppNavigation(viewModel: ExerciseViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "insertExerciseAndSet") {
        composable("insertExerciseAndSet") {
            InsertExerciseAndSetScreen(navController,viewModel)
        }
        composable("exerciseSetList") {
            ExerciseSetListScreen()
        }
    }
}