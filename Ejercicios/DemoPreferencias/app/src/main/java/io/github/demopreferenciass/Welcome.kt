package io.github.demopreferenciass

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val lblDatos = findViewById<TextView>(R.id.lblBienvenida)
        val sharedPreferences = getSharedPreferences("MISDATOS", Context.MODE_PRIVATE)
        val nombreUsuario = sharedPreferences.getString("nombreDeUsuario","").toString()
        lblDatos.text = "Bienvenido : $nombreUsuario"
    }
}