package com.ipn.operaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Operaciones : AppCompatActivity() {
    lateinit var numero : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operaciones)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular);
        val lblResultado = findViewById<TextView>(R.id.lblResultado);

        numero = findViewById<EditText>(R.id.txtNumero)
        btnCalcular.setOnClickListener(){
            val factorial = factorial(numero.text.toString().toInt())
            Toast.makeText(this,
            "El resultado es "+ factorial,
            Toast.LENGTH_LONG).show()
            val lblResultado = findViewById<TextView>(R.id.lblResultado)
            .apply {
                text = factorial.toString()
            }
        }
    }

    private fun factorial(numero: Int): Int {
        var resultado = 1
        for(x in 1..numero){
            resultado *= x
        }
        return resultado
    }
}