package com.ipn.eventos.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ipn.eventos.R
import com.ipn.eventos.data.db.EventosDatabase
import com.ipn.eventos.data.db.entities.Evento
import java.text.SimpleDateFormat
import java.util.*

/**
 * Actividad para mostrar y modificar los detalles de un evento.
 * Permite al usuario editar el nombre, descripción y fecha del evento.
 */
class EventoActivity : AppCompatActivity() {

    lateinit var txtNombre: EditText
    lateinit var txtDescripcion: EditText
    lateinit var txtFecha: EditText
    lateinit var btnCancelar: Button
    lateinit var btnModificar: Button
    lateinit var btnRegresar: Button

    private lateinit var eventosDatabase: EventosDatabase
    private lateinit var evento: Evento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evento)

        // Obtener el ID del evento de los extras del intent
        val eventoId = intent.getIntExtra("evento_id", -1)

        if (eventoId == -1) {
            finish()
        }

        eventosDatabase = EventosDatabase.getInstance(applicationContext)
        evento = eventosDatabase.eventoDao().read(eventoId)

        txtNombre = findViewById(R.id.txtNombre)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtFecha = findViewById(R.id.txtFecha)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnModificar = findViewById(R.id.btnModificar)
        btnRegresar = findViewById(R.id.btnRegresar)

        actualizarCampos()

        btnCancelar.setOnClickListener {
            borrarCampos()
        }

        btnRegresar.setOnClickListener {
            finish()
        }

        btnModificar.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val descripcion = txtDescripcion.text.toString()
            val fechaString = txtFecha.text.toString()

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fechaUtil: Date = dateFormat.parse(fechaString) as Date
            val fecha = java.sql.Date(fechaUtil.time)

            // Actualizar los datos del evento
            evento.nombreEvento = nombre
            evento.descripcionEvento = descripcion
            evento.fechaEvento = fecha

            // Guardar el evento actualizado en la base de datos
            eventosDatabase.eventoDao().update(evento)

            // Mostrar un mensaje de toast u realizar otras acciones después de actualizar el evento
            Toast.makeText(this, "¡Evento actualizado!", Toast.LENGTH_LONG).show()

            actualizarCampos()
        }
    }

    /**
     * Borra los campos de texto.
     */
    private fun borrarCampos() {
        txtNombre.text.clear()
        txtDescripcion.text.clear()
        txtFecha.text.clear()
    }

    /**
     * Actualiza los campos de texto con los datos del evento actual.
     */
    private fun actualizarCampos() {
        txtNombre.setText(evento.nombreEvento)
        txtDescripcion.setText(evento.descripcionEvento)
        txtFecha.setText(evento.fechaEvento.toString())
    }
}
