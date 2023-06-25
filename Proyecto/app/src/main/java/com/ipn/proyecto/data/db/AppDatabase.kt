package com.ipn.proyecto.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipn.proyecto.data.db.dao.TaskDao
import com.ipn.proyecto.data.db.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}