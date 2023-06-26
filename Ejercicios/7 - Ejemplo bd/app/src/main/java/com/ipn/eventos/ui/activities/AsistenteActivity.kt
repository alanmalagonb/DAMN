package com.ipn.eventos.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ipn.eventos.R
import com.ipn.eventos.data.db.EventosDatabase
import com.ipn.eventos.data.db.entities.Asistente
import com.ipn.eventos.data.db.entities.Evento
import java.text.SimpleDateFormat
import java.util.*

/**
 * Actividad para mostrar y modificar los detalles de un asistente.
 * Permite al usuario editar los campos del asistente y guardar los cambios en la base de datos.
 */
class AsistenteActivity : AppCompatActivity() {

    lateinit var txtNombre: EditText
    lateinit var txtPaterno: EditText
    lateinit var txtMaterno: EditText
    lateinit var txtEmail: EditText
    lateinit var btnCancelar: Button
    lateinit var btnModificar: Button
    lateinit var btnRegresar: Button

    private lateinit var eventosDatabase: EventosDatabase
    private lateinit var asistente: Asistente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistente)

        // Obtener los extras del intent
        val asistenteId = intent.getIntExtra("asistente_id", -1)

        if (asistenteId == -1) finish()

        eventosDatabase = EventosDatabase.getInstance(applicationContext)
        asistente = eventosDatabase.asistenteDao().read(asistenteId)

        txtNombre = findViewById(R.id.txtNombre)
        txtPaterno = findViewById(R.id.txtPaterno)
        txtMaterno = findViewById(R.id.txtMaterno)
        txtEmail = findViewById(R.id.txtEmail)

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
            val paterno = txtMaterno.text.toString()
            val materno = txtPaterno.text.toString()
            val email = txtEmail.text.toString()

            // Actualizar los datos del asistente
            asistente.nombreAsistente = nombre
            asistente.paternoAsistente = paterno
            asistente.maternoAsistente = materno
            asistente.emailAsistente = email

            // Guardar el asistente actualizado en la base de datos
            eventosDatabase.asistenteDao().update(asistente)

            // Mostrar un mensaje toast u realizar cualquier otra acción después de actualizar el asistente
            Toast.makeText(this, "Asistente Actualizado!", Toast.LENGTH_LONG).show()

            actualizarCampos()
        }
    }

    private fun borrarCampos() {
        txtNombre.text.clear()
        txtPaterno.text.clear()
        txtMaterno.text.clear()
        txtEmail.text.clear()
    }

    private fun actualizarCampos() {
        txtNombre.setText(asistente.nombreAsistente)
        txtPaterno.setText(asistente.paternoAsistente)
        txtMaterno.setText(asistente.maternoAsistente)
        txtEmail.setText(asistente.emailAsistente)
    }
}
