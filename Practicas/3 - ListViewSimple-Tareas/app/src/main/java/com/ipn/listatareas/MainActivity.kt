package com.ipn.listatareas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener la referencias a los elementos UI
        val listViewTarea = findViewById<ListView>(R.id.listViewTarea)
        val editTextTarea = findViewById<EditText>(R.id.editTextArea)

        // Crear un array
        val tareas = ArrayList<String>()
        tareas.add("Aprender Kotlin")
        tareas.add("Aprender Android")
        val adaptador: ArrayAdapter<String>
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,tareas
        )
        listViewTarea.adapter = adaptador
        btnAgregar.setOnClickListener(){
            tareas.add(editTextTarea.text.toString())
            editTextTarea.setText("")
            adaptador.notifyDataSetChanged()
        }

        listViewTarea.onItemClickListener = object: OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, posicion: Int, p3: Long) {
                // valor del elemento en el que se hace clic
                val elementoDeLaLista = listViewTarea.getItemAtPosition(posicion) as String
                Toast.makeText(
                    applicationContext,
                    "Elemento seleccionado : $posicion\n valor del elemento: $elementoDeLaLista",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
}