package com.ipn.proyecto.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipn.proyecto.data.db.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)
}