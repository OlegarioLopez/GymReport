package com.olelopez.gymreport.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.olelopez.gymreport.data.ExerciseSetDao
import com.olelopez.gymreport.ui.dataUI.ExerciseSetWithExerciseName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExerciseSetViewModel(private val exerciseSetDao: ExerciseSetDao) : ViewModel() {
    private val _exerciseSets = MutableStateFlow<List<ExerciseSetWithExerciseName>>(emptyList())
    val exerciseSets: StateFlow<List<ExerciseSetWithExerciseName>> = _exerciseSets.asStateFlow()

    init {
        viewModelScope.launch {
            exerciseSetDao.getExerciseSetsWithExerciseName().collect { sets ->
                _exerciseSets.value = sets
            }
        }
    }

    class Factory(private val exerciseSetDao: ExerciseSetDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExerciseSetViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ExerciseSetViewModel(exerciseSetDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}