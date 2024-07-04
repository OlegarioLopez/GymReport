package com.olelopez.gymreport.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olelopez.gymreport.data.Exercise
import com.olelopez.gymreport.data.ExerciseDao
import com.olelopez.gymreport.data.ExerciseSet
import com.olelopez.gymreport.data.ExerciseSetDao
import com.olelopez.gymreport.ui.dataUI.ExerciseSetWithExerciseName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class ExerciseViewModel(
    private val exerciseDao: ExerciseDao,
    private val exerciseSetDao: ExerciseSetDao
) : ViewModel() {

    private val _exerciseSetsWithNames = MutableStateFlow<List<ExerciseSetWithExerciseName>>(emptyList())
    val exerciseSetsWithNames: StateFlow<List<ExerciseSetWithExerciseName>> = _exerciseSetsWithNames.asStateFlow()


    init {
        viewModelScope.launch {
            exerciseSetDao.getExerciseSetsWithExerciseName().collect { sets ->
                _exerciseSetsWithNames.value = sets
            }
        }
    }
    fun insertExercise(
        name: String,
        muscleGroup: String,
        repetitions: Int,
        intensity: Int,
        date: Date,
        userId: Long,
        kg: Float
    ) {
        viewModelScope.launch {
            val exercise = Exercise(name = name, muscleGroup = muscleGroup)
            val exerciseId = exerciseDao.insert(exercise)

            val exerciseSet = ExerciseSet(
                exerciseId = exerciseId,
                repetitions = repetitions,
                intensity = intensity,
                date = date,
                userId = userId,
                kg = kg
            )
            exerciseSetDao.insert(exerciseSet)
        }
    }
}
