package com.olelopez.gymreport.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.olelopez.gymreport.data.typeConverters.Converters


@Database(entities = [User::class, Exercise::class, ExerciseSet::class], version = 1 ,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseSetDao(): ExerciseSetDao
}