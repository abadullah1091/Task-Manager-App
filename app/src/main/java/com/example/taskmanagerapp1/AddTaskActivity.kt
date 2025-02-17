package com.example.taskmanagerapp1


import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagerapp1.databinding.ActivityAddTaskBinding
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var database: TaskDatabase
    private var dueDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = TaskDatabase.getDatabase(this)

        binding.buttonSelectDate.setOnClickListener { pickDueDate() }

        binding.buttonSave.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()
            val isCompleted = binding.checkCompleted.isChecked

            if (title.isNotEmpty() && dueDate.isNotEmpty()) {
                val newTask = Task(title = title, description = description, dueDate = dueDate, isCompleted = isCompleted)
                database.taskDao().insert(newTask)
                finish()
            } else {
                binding.editTitle.error = "Title is required"
            }
        }
    }

    private fun pickDueDate() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, day ->
            dueDate = "$year-${month + 1}-$day"
            binding.textDueDate.text = dueDate
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
}
