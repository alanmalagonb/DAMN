package com.ipn.operaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Bienvenido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenido)

        val textoAMostrar = "Bienvenido " + intent.getStringExtra("NOMBRE") + " " + intent.getStringExtra("CLAVE")
        val lblDatos = findViewById<TextView>(R.id.lblDatos)
            .apply {
                text = textoAMostrar
            }

        findViewById<Button>(R.id.btnOperaciones).setOnClickListener(){
            val operacionesIt = Intent(this, Operaciones::class.java)
            startActivity(operacionesIt)
        }

        findViewById<Button>(R.id.btnDE).setOnClickListener(){
            val deIt = Intent(this, DesviacionEstandar::class.java)
            startActivity(deIt)
        }
    }
}