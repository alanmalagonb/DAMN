package com.ipn.proyecto.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.ipn.proyecto.R
import com.ipn.proyecto.data.db.dao.TaskDao
import com.ipn.proyecto.data.db.entities.Task

class TaskAdapter(private val context: Context, private val taskDao: TaskDao) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var tasks: List<Task> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTextView: TextView = itemView.findViewById(R.id.taskTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        private val editButton: ImageButton = itemView.findViewById(R.id.editButton)

        fun bind(task: Task) {
            taskTextView.text = task.name

            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val taskToDelete = tasks[position]
                    deleteTaskFromDatabase(taskToDelete)
                }
            }

            editButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val taskToEdit = tasks[position]
                    showEditDialog(taskToEdit)
                }
            }
        }
    }

    private fun deleteTaskFromDatabase(task: Task) {
            taskDao.deleteTask(task)
    }

    private fun showEditDialog(task: Task) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Editar Tarea")
        val editText = EditText(context)
        editText.setText(task.name)
        builder.setView(editText)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val updatedTaskName = editText.text.toString()
            if (updatedTaskName.isNotBlank()) {
                updateTaskInDatabase(task, updatedTaskName)
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun updateTaskInDatabase(task: Task, updatedTaskName: String) {
        val updatedTask = task.copy(name = updatedTaskName)
        taskDao.updateTask(updatedTask)
    }

}