package com.ipn.operaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DesviacionEstandar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desviacion_estandar)

        findViewById<Button>(R.id.btnCalcularDE).setOnClickListener(){
            val numArray = findViewById<EditText>(R.id.txtDE).text.toString().split(",").map { it.toDouble() }
            val SD = calcularDE(numArray)
            val lblDE = findViewById<TextView>(R.id.lblDE).apply {
                text = SD.toString()
            }
        }
    }

    fun calcularDE(numArray: List<Double>): Double {
        var sum = 0.0
        var standardDeviation = 0.0

        for (num in numArray) {
            sum += num
        }

        val mean = sum / numArray.size

        for (num in numArray) {
            standardDeviation += Math.pow(num - mean, 2.0)
        }

        return Math.sqrt(standardDeviation / numArray.size)
    }
}