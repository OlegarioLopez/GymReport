package com.olelopez.gymreport.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "set_table")
data class ExerciseSet(
    @PrimaryKey(autoGenerate = true)
    val setId: Long = 0L,
    val exerciseId: Long,
    val repetitions: Int,
    val intensity: Int,
    val date: Date,
    val userId: Long
)