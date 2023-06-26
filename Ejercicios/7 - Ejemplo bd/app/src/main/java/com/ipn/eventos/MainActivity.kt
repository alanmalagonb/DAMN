package com.ipn.eventos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipn.eventos.data.db.EventosDatabase
import com.ipn.eventos.data.db.entities.Evento
import com.ipn.eventos.ui.adapters.EventosAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Clase principal de la actividad principal de la aplicación.
 * Esta clase extiende AppCompatActivity y gestiona la interfaz de usuario y la lógica de la aplicación.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var txtDescripcion: EditText
    private lateinit var txtFecha: EditText
    private lateinit var btnCancelar: Button
    private lateinit var btnAgregar: Button

    private lateinit var recyclerViewEventos: RecyclerView
    private lateinit var eventosAdapter: EventosAdapter

    private lateinit var eventosDatabase: EventosDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar los elementos de la interfaz de usuario
        txtNombre = findViewById(R.id.txtNombre)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtFecha = findViewById(R.id.txtFecha)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnAgregar = findViewById(R.id.btnModificar)

        // Obtener una instancia de la base de datos
        eventosDatabase = EventosDatabase.getInstance(applicationContext)

        // Configurar el botón de cancelar
        btnCancelar.setOnClickListener {
            borrarCampos()
        }

        // Configurar el botón de agregar
        btnAgregar.setOnClickListener {
            agregarEvento()
        }

        // Configurar el RecyclerView y el adaptador
        recyclerViewEventos = findViewById(R.id.recyclerViewEventos)
        recyclerViewEventos.layoutManager = LinearLayoutManager(this)

        eventosAdapter = EventosAdapter(this, eventosDatabase)
        recyclerViewEventos.adapter = eventosAdapter
    }

    /**
     * Borra los campos de texto en la interfaz de usuario.
     */
    private fun borrarCampos() {
        txtNombre.text.clear()
        txtDescripcion.text.clear()
        txtFecha.text.clear()
    }

    /**
     * Agrega un nuevo evento a la base de datos y actualiza el adaptador del RecyclerView.
     */
    private fun agregarEvento() {
        // Obtener los valores ingresados en los campos de texto
        val nombre = txtNombre.text.toString()
        val descripcion = txtDescripcion.text.toString()
        val fechaString = txtFecha.text.toString()

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechaUtil: Date = dateFormat.parse(fechaString) as Date
        val fecha = java.sql.Date(fechaUtil.time)

        val eventoDao = eventosDatabase.eventoDao()

        // Crear un objeto Evento con los datos ingresados
        val evento = Evento(0, nombre, descripcion, fecha)

        // Guardar el evento en la base de datos utilizando el DAO
        eventoDao.insert(evento)

        // Actualizar el adaptador del RecyclerView con la nueva lista de eventos
        eventosAdapter.actualizarEventos()
    }
}
