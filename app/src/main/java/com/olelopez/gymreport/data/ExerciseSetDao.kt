package com.olelopez.gymreport.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseSetDao {
    @Insert
    suspend fun insert(exerciseSet: ExerciseSet)

    @Query("SELECT * FROM set_table WHERE exerciseId = :exerciseId")
    suspend fun getSetsForExercise(exerciseId: Long): List<ExerciseSet>
}