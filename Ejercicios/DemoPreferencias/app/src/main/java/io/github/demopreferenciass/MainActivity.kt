package io.github.demopreferenciass

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("MISDATOS", Context.MODE_PRIVATE)
        val nombreUsuario = sharedPreferences.getString("nombreDeUsuario","")
        val claveDelUsuario = sharedPreferences.getString("claveDelUsuario","")

        val btnAceptar = findViewById<Button>(R.id.btnAceptar)
        val txtUsuario = findViewById<EditText>(R.id.txtUsuario)
        val txtClave = findViewById<EditText>(R.id.txtClave)
        val chkRecuerdame =findViewById<CheckBox>(R.id.chkRecuerdame)

        if(nombreUsuario.isNullOrEmpty() || claveDelUsuario.isNullOrEmpty()){

        }

        btnAceptar.setOnClickListener(){
            val editor = sharedPreferences.edit()
            editor.putString("nombreDeUsuario",txtUsuario.text.toString())
            editor.putString("claveDelUsuario",txtClave.text.toString())
            editor.apply()
        }

    }
}