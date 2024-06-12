package com.olelopez.gymreport.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olelopez.gymreport.data.Exercise
import com.olelopez.gymreport.data.ExerciseDao
import com.olelopez.gymreport.data.ExerciseSet
import com.olelopez.gymreport.data.ExerciseSetDao
import kotlinx.coroutines.launch
import java.util.*

class ExerciseViewModel(
    private val exerciseDao: ExerciseDao,
    private val exerciseSetDao: ExerciseSetDao
) : ViewModel() {

    fun insertExercise(
        name: String,
        muscleGroup: String,
        repetitions: Int,
        intensity: Int,
        date: Date,
        userId: Long
    ) {
        viewModelScope.launch {
            val exercise = Exercise(name = name, muscleGroup = muscleGroup)
            val exerciseId = exerciseDao.insert(exercise)

            val exerciseSet = ExerciseSet(
                exerciseId = exerciseId,
                repetitions = repetitions,
                intensity = intensity,
                date = date,
                userId = userId
            )
            exerciseSetDao.insert(exerciseSet)
        }
    }
}
