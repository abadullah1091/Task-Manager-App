package com.example.taskmanagerapp1


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile private var instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java, "task_database"
                ).allowMainThreadQueries().build()
                instance = newInstance
                newInstance
            }
        }
    }
}
