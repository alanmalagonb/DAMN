package com.ipn.temperaturas

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var spinner : Spinner
    lateinit var spinnerTo : Spinner
    var char1: Char = 'C'
    var char2: Char = 'C'

    lateinit var txtDegrees: EditText
    lateinit var lblDegress: TextView
    lateinit var btnConvert : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.selType)
        spinnerTo = findViewById(R.id.selTypeTo)
        txtDegrees = findViewById(R.id.txtDegrees)
        lblDegress = findViewById(R.id.lblDegrees)
        btnConvert = findViewById(R.id.btnConvert)

        ArrayAdapter.createFromResource(
            this,
        R.array.types_array,
        android.R.layout.simple_spinner_item
        ).also{
            adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinnerTo.adapter = adapter
        }

        spinner.onItemSelectedListener = this
        spinnerTo.onItemSelectedListener = this


        btnConvert.setOnClickListener(){

            if(txtDegrees.text.toString().isNullOrEmpty()){
                Toast.makeText(this,"Ingresa grados",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(char1.equals(char2)){
                Toast.makeText(this,"Selecciona grados diferentes",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TemperatureApi::class.java)

           val retrofitData = retrofit.convertTemperature(txtDegrees.text.toString().toDouble(), char1, char2)

            retrofitData.enqueue(object : Callback<TemperatureResponse?> {
                override fun onResponse(
                    call: Call<TemperatureResponse?>,
                    response: Response<TemperatureResponse?>
                ) {
                    val responseBody = response.body()!!
                    val sb = StringBuilder()
                    sb.append(responseBody.degrees).append(" °")
                        .append(responseBody.type)
                    lblDegress.text = sb.toString()
                }

                override fun onFailure(call: Call<TemperatureResponse?>, t: Throwable) {
                    val temperature: Optional<Temperature> = convertTemperature(txtDegrees.text.toString().toDouble(), char1, char2)
                    if (temperature.isPresent) {
                        var temperature = temperature.get()
                        val sb = StringBuilder()
                        sb.append(temperature.degrees).append(" °")
                            .append(temperature.type)
                        lblDegress.text = sb.toString()
                    }
                }
            })
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // Get the selected value from the Spinner
        val selectedItem = parent.getItemAtPosition(pos).toString()

        // Update the corresponding variable based on which Spinner was used
        if (parent.id == R.id.selType) {
            char1 = selectedItem[0]
        } else if (parent.id == R.id.selTypeTo) {
            char2 = selectedItem[0]
        }

        // For debugging purposes, you can print the selected values to the console
        Log.d("MainActivity", "char1 = $char1, char2 = $char2")
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    fun convertTemperature(degrees: Double, type: Char, typeTo: Char): Optional<Temperature> {
        var optional: Optional<Temperature> = Optional.empty()
        val convertedDegrees: Double

        // convert from Celsius to Fahrenheit
        convertedDegrees = if (type == 'C' && typeTo == 'F') {
            degrees * 1.8 + 32
        } else if (type == 'C' && typeTo == 'K') {
            degrees + 273.15
        } else if (type == 'F' && typeTo == 'C') {
            (degrees - 32) / 1.8
        } else if (type == 'F' && typeTo == 'K') {
            (degrees + 459.67) / 1.8
        } else if (type == 'K' && typeTo == 'C') {
            degrees - 273.15
        } else if (type == 'K' && typeTo == 'F') {
            degrees * 1.8 - 459.67
        } else {
            return optional
        }
        optional = Optional.of(Temperature(convertedDegrees, typeTo))
        return optional
    }
}