package com.ipn.alumnos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var alumnosAdapter: AlumnosAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)
        alumnosAdapter = AlumnosAdapter()

        recyclerView = findViewById(R.id.recyclerViewAlumnos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = alumnosAdapter

        val btnAgregar = findViewById<Button>(R.id.buttonGuardar)
        btnAgregar.setOnClickListener {
            agregarAlumno()
        }

        mostrarAlumnos()
    }

    private fun agregarAlumno() {
        val etNombre = findViewById<EditText>(R.id.editTextNombre)
        val etApellidoPaterno = findViewById<EditText>(R.id.editTextApellidoPaterno)
        val etApellidoMaterno = findViewById<EditText>(R.id.editTextApellidoMaterno)
        val etEmail = findViewById<EditText>(R.id.editTextEmail)

        val nombre = etNombre.text.toString()
        val apellidoPaterno = etApellidoPaterno.text.toString()
        val apellidoMaterno = etApellidoMaterno.text.toString()
        val email = etEmail.text.toString()
        val fechaCreacion = Date()

        val alumno = Alumno(0, nombre, apellidoPaterno, apellidoMaterno, email, fechaCreacion)
        databaseHelper.insertAlumno(alumno)

        mostrarAlumnos()

        // Limpiar los campos de texto después de agregar el alumno
        etNombre.text.clear()
        etApellidoPaterno.text.clear()
        etApellidoMaterno.text.clear()
        etEmail.text.clear()
    }

    private fun mostrarAlumnos() {
        val listaAlumnos = databaseHelper.getAllAlumnos()
        alumnosAdapter.actualizarAlumnos(listaAlumnos)
    }
}
