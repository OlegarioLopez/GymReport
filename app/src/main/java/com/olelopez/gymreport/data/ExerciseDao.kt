package com.olelopez.gymreport.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: Exercise): Long

    @Query("SELECT * FROM exercise_table")
    suspend fun getAllExercises(): List<Exercise>
}