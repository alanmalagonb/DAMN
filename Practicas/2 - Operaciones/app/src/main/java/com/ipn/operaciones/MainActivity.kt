package com.ipn.operaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bienvenidoActividad();

    }

    fun bienvenidoActividad() {
        findViewById<Button>(R.id.btnAceptar).setOnClickListener(){
            val nombreUsuario = findViewById<EditText>(R.id.txtUsuario).text.toString().trim()
            val claveUsuario = findViewById<EditText>(R.id.txtClave).text.toString().trim()
            if(nombreUsuario.isNotEmpty()){
                val bienvenidoIt = Intent(this,Bienvenido::class.java).also {
                    it.putExtra("NOMBRE",nombreUsuario)
                    it.putExtra("CLAVE",claveUsuario)
                    startActivity(it)
                }
            }
        }
    }


}