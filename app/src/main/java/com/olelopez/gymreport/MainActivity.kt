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
import androidx.room.Room
import com.olelopez.gymreport.data.AppDatabase
import com.olelopez.gymreport.data.ExerciseSet
import com.olelopez.gymreport.ui.theme.GymReportTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.Instant.now
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()


        val exerciseId = 1L
        val repetitions = 10
        val intensity = 8
        val date = Date() // Fecha actual
        val userId = 1L
        val exerciseSet = ExerciseSet(2L,exerciseId,4,8, Date(),1)
        var exerciseSetDao = db.exerciseSetDao()
        // Llamada para insertar en la base de datos
        GlobalScope.launch(Dispatchers.IO) {
            exerciseSetDao.insert(exerciseSet)
        }
        setContent {
            GymReportTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
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