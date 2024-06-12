package com.olelopez.gymreport.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olelopez.gymreport.ui.ExerciseViewModel
import java.util.*

@Composable
fun InsertExerciseAndSetScreen(viewModel: ExerciseViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var muscleGroup by remember { mutableStateOf("") }
    var repetitions by remember { mutableStateOf("") }
    var intensity by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(Date()) } // Default to current date
    var userId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Exercise Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = muscleGroup,
            onValueChange = { muscleGroup = it },
            label = { Text("Muscle Group") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = repetitions,
            onValueChange = { repetitions = it },
            label = { Text("Repetitions") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = intensity,
            onValueChange = { intensity = it },
            label = { Text("Intensity") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.insertExercise(
                    name,
                    muscleGroup,
                    repetitions.toIntOrNull() ?: 0,
                    intensity.toIntOrNull() ?: 0,
                    date,
                    userId.toLongOrNull() ?: 0L
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Exercise")
        }
    }
}
