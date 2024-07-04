package com.olelopez.gymreport.ui.dataUI

import java.util.Date

data class ExerciseSetWithExerciseName(
    val setId: Long,
    val exerciseName: String,
    val muscleGroup: String,
    val repetitions: Int,
    val intensity: Int,
    val date: Date,
    val userId: Long,
    val kg: Float
)