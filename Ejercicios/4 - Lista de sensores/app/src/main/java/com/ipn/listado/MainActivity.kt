package com.ipn.listado

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var sm : SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var listaDeSensores : List<Sensor> = sm.getSensorList(Sensor.TYPE_ALL)


        var listaSensores = findViewById<ListView>(R.id.listaSensores)
        var adapter = ArrayAdapter<Sensor>(applicationContext,android.R.layout.simple_dropdown_item_1line,listaDeSensores)
        listaSensores.adapter = adapter



        var txtLista = findViewById<TextView>(R.id.txtLista)

        for (i in listaDeSensores.indices){
            txtLista.append("Nombre : ${listaDeSensores.get(i)} \n \n \n ")
        }

    }
}