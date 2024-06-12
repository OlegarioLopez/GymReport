package com.olelopez.gymreport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.olelopez.gymreport.data.AppDatabase
import com.olelopez.gymreport.data.ExerciseSet
import com.olelopez.gymreport.ui.ExerciseViewModel
import com.olelopez.gymreport.ui.composables.InsertExerciseAndSetScreen
import com.olelopez.gymreport.ui.factories.ExerciseViewModelFactory
import com.olelopez.gymreport.ui.theme.GymReportTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.Instant.now
import java.util.Date

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
            GymReportTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InsertExerciseAndSetScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GymReportTheme {
        Greeting("Android")
    }
}