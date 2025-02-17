package com.example.taskmanagerapp1


import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM Task ORDER BY dueDate ASC")
    fun getAllTasks(): List<Task>
}
