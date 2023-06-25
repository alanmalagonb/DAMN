package com.ipn.proyecto

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ipn.proyecto.data.db.AppDatabase
import com.ipn.proyecto.data.db.dao.TaskDao
import com.ipn.proyecto.data.db.entities.Task
import com.ipn.proyecto.ui.adapters.TaskAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var taskDao: TaskDao
    private lateinit var adapter: TaskAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var themeSpinner: AppCompatSpinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "task-db").allowMainThreadQueries().build()
        taskDao = database.taskDao()

        adapter = TaskAdapter(this,taskDao)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        taskDao.getAllTasks().observe(this, { tasks ->
            adapter.setTasks(tasks)
        })

        val addButton: Button = findViewById(R.id.addButton)
        val taskEditText: EditText = findViewById(R.id.taskEditText)

        addButton.setOnClickListener {
            val taskName = taskEditText.text.toString()
            val task = Task(name = taskName)
            taskDao.insertTask(task)
            taskEditText.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val themeItem = menu.findItem(R.id.menu_theme)
        themeSpinner = themeItem.actionView as AppCompatSpinner

        val themes = arrayOf("Light", "Dark")
        val themeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, themes)
        themeSpinner.adapter = themeAdapter

        // Obtaining the saved theme preference from Shared Preferences and setting it as the selected item
        val currentTheme = sharedPreferences.getString("theme", "Light")
        val currentThemeIndex = themes.indexOf(currentTheme)
        themeSpinner.setSelection(currentThemeIndex)

        themeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTheme = themes[position]
                setAppTheme(selectedTheme)
                saveThemePreference(selectedTheme)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return true
    }

    private fun setAppTheme(theme: String) {
        when (theme) {
            "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun saveThemePreference(theme: String) {
        editor.putString("theme", theme)
        editor.apply()
    }
}