package com.olelopez.gymreport.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.olelopez.gymreport.ui.dataUI.ExerciseSetWithExerciseName
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseSetDao {
    @Insert
    suspend fun insert(exerciseSet: ExerciseSet)

    @Query("SELECT * FROM set_table WHERE exerciseId = :exerciseId")
    suspend fun getSetsForExercise(exerciseId: Long): List<ExerciseSet>

    @Query("""
        SELECT 
            s.setId, 
            e.name AS exerciseName, 
            e.muscleGroup, 
            s.repetitions, 
            s.intensity, 
            s.date, 
            s.userId,
            s.kg
        FROM set_table s
        INNER JOIN exercise_table e ON s.exerciseId = e.exerciseId
        ORDER BY s.date DESC
    """)
    fun getExerciseSetsWithExerciseName(): Flow<List<ExerciseSetWithExerciseName>>
}