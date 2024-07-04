package com.olelopez.gymreport.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.olelopez.gymreport.R
import com.olelopez.gymreport.ui.ExerciseViewModel
import java.util.*

@Composable
fun InsertExerciseAndSetScreen(navController: NavController, viewModel: ExerciseViewModel) {
    var name by remember { mutableStateOf("") }
    var muscleGroup by remember { mutableStateOf("") }
    var repetitions by remember { mutableStateOf("") }
    var intensity by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(Date()) } // Default to current date
    var userId by remember { mutableStateOf("") }
    var kg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.exercise_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = muscleGroup,
            onValueChange = { muscleGroup = it },
            label = { Text(stringResource(R.string.muscle_group)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = kg,
            onValueChange = { kg = it },
            label = { Text(stringResource(R.string.kg)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = repetitions,
            onValueChange = { repetitions = it },
            label = { Text(stringResource(R.string.repetitions)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = intensity,
            onValueChange = { intensity = it },
            label = { Text(stringResource(R.string.intensity)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text(stringResource(R.string.user_id)) },
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
                    userId.toLongOrNull() ?: 0L,
                    kg.toFloatOrNull() ?: 0.0f
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.add_exercise))
        }
        Button(
            onClick = {
                navController.navigate("exerciseSetList")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Visualize Sets")
        }
    }
}
