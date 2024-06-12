package com.olelopez.gymreport.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olelopez.gymreport.data.ExerciseDao
import com.olelopez.gymreport.data.ExerciseSetDao
import com.olelopez.gymreport.ui.ExerciseViewModel

class ExerciseViewModelFactory(
    private val exerciseDao: ExerciseDao,
    private val exerciseSetDao: ExerciseSetDao
) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseViewModel(exerciseDao, exerciseSetDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
