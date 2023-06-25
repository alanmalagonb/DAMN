package com.ipn.eventos.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipn.eventos.R
import com.ipn.eventos.data.db.EventosDatabase
import com.ipn.eventos.data.db.entities.Asistente
import com.ipn.eventos.ui.adapters.AsistentesAdapter
import java.util.*

/**
 * Actividad para mostrar y agregar asistentes a un evento.
 * Permite al usuario ingresar el nombre, apellido paterno, apellido materno y correo electrónico del asistente.
 */
class AsistentesActivity : AppCompatActivity() {

    lateinit var txtNombre: EditText
    lateinit var txtPaterno: EditText
    lateinit var txtMaterno: EditText
    lateinit var txtEmail: EditText
    lateinit var btnCancelar: Button
    lateinit var btnAgregar: Button

    private lateinit var recyclerViewAsistentes: RecyclerView
    private lateinit var asistentesAdapter: AsistentesAdapter

    private lateinit var eventosDatabase: EventosDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistentes)

        val idEvento: Int = intent.getIntExtra("evento_id", -1)

        if (idEvento == -1) {
            finish()
        }

        txtNombre = findViewById(R.id.txtNombre)
        txtPaterno = findViewById(R.id.txtPaterno)
        txtMaterno = findViewById(R.id.txtMaterno)
        txtEmail = findViewById(R.id.txtEmail)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnAgregar = findViewById(R.id.btnModificar)

        eventosDatabase = EventosDatabase.getInstance(applicationContext)

        btnCancelar.setOnClickListener {
            borrarCampos()
        }

        btnAgregar.setOnClickListener {
            agregarAsistente(idEvento)
        }

        recyclerViewAsistentes = findViewById(R.id.recyclerViewAsistentes)
        recyclerViewAsistentes.layoutManager = LinearLayoutManager(this)

        asistentesAdapter = AsistentesAdapter(this, eventosDatabase, idEvento)
        recyclerViewAsistentes.adapter = asistentesAdapter
    }

    /**
     * Borra los campos de texto.
     */
    private fun borrarCampos() {
        txtNombre.text.clear()
        txtPaterno.text.clear()
        txtMaterno.text.clear()
        txtEmail.text.clear()
    }

    /**
     * Agrega un nuevo asistente al evento.
     */
    private fun agregarAsistente(idEvento: Int) {
        // Obtener los valores ingresados en los campos de texto
        val nombre = txtNombre.text.toString()
        val paterno = txtMaterno.text.toString()
        val materno = txtPaterno.text.toString()
        val email = txtEmail.text.toString()

        val asistenteDao = eventosDatabase.asistenteDao()

        // Crear un objeto Asistente con los datos ingresados
        val asistente = Asistente(0, nombre, paterno, materno, email, idEvento)

        // Guardar el asistente en la base de datos utilizando el DAO
        asistenteDao.insert(asistente)

        // Actualizar el adaptador del RecyclerView con la nueva lista de asistentes
        asistentesAdapter.actualizarAsistentes()
    }
}
