import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olelopez.gymreport.LocalExerciseSetDao
import com.olelopez.gymreport.ui.ExerciseSetViewModel
import com.olelopez.gymreport.ui.dataUI.ExerciseSetWithExerciseName
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun ExerciseSetListScreen(
    modifier: Modifier = Modifier,
    viewModel: ExerciseSetViewModel = viewModel(
        factory = ExerciseSetViewModel.Factory(LocalExerciseSetDao.current)
    )
) {
    val exerciseSets by viewModel.exerciseSets.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Exercise Set List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(exerciseSets) { exerciseSet ->
                ExerciseSetItem(exerciseSet)
            }
        }
    }
}

@Composable
fun ExerciseSetItem(exerciseSet: ExerciseSetWithExerciseName) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = exerciseSet.exerciseName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Muscle Group: ${exerciseSet.muscleGroup}")
            Text("Reps: ${exerciseSet.repetitions}")
            Text("Intensity: ${exerciseSet.intensity}")
            Text("Date: ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(exerciseSet.date)}")
        }
    }
}