package com.olelopez.gymreport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.olelopez.gymreport.data.AppDatabase
import com.olelopez.gymreport.data.ExerciseSetDao
import com.olelopez.gymreport.ui.ExerciseViewModel
import com.olelopez.gymreport.ui.factories.ExerciseViewModelFactory
import com.olelopez.gymreport.ui.navigation.AppNavigation
import com.olelopez.gymreport.ui.theme.GymReportTheme

val LocalExerciseSetDao = staticCompositionLocalOf<ExerciseSetDao> { error("No ExerciseSetDao provided") }

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val exerciseDao = db.exerciseDao()
        val exerciseSetDao = db.exerciseSetDao()
        val viewModelFactory = ExerciseViewModelFactory(exerciseDao, exerciseSetDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)

        setContent {
            CompositionLocalProvider(LocalExerciseSetDao provides db.exerciseSetDao()) {

            GymReportTheme {

                AppNavigation(viewModel)

            }
        }
    }
}
}
