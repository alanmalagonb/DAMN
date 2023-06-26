package com.ipn.actividadeseintenciones

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Bienvenido : AppCompatActivity() {
    // Definicion de las variables
    private lateinit var preferencias: DarkPreferenceManager

    // Variables de apoyo
    private lateinit var usuario: String
    private lateinit var lblDatos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenido)
        inicializar()
        verificarLogin()
    }

    private fun inicializar() {
        preferencias = DarkPreferenceManager(this)
        usuario = preferencias.getNombreDeUsuario().toString()
        lblDatos = findViewById(R.id.lblBienvenido)
        setLblDatos()
    }

    private fun verificarLogin(){
        if(preferencias.isLogin() == false){
            val mainIntent = Intent(this,MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }

    private fun setLblDatos(){
        lblDatos.text = "Bienvenido : $usuario"
    }

    fun cerrarSesion(view: View){
        preferencias.limpiarPreferencias()
        val mainIntent = Intent(this,MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    fun correo(view: View){
        val enviarCorreo = Intent().apply{
            action = Intent.ACTION_SEND
            data=Uri.parse("mailto.mail@mail.com")
        }
        startActivity(Intent.createChooser(enviarCorreo,"Edge"))
    }

    fun telefono(view: View){
        val llamar = Intent().apply{
            action = Intent.ACTION_DIAL
            data= Uri.parse("tel:00000000")
        }
        startActivity(llamar)
    }
}


