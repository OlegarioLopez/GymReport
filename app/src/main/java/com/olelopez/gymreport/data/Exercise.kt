package com.olelopez.gymreport.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long = 0L,
    val name: String,
    val muscleGroup: String
)