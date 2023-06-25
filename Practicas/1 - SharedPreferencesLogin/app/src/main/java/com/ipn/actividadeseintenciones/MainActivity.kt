package com.ipn.actividadeseintenciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    // Definicion de las variables
    private lateinit var preferencias: DarkPreferenceManager
    private lateinit var correoElectronico: EditText
    private lateinit var claveUsuario: EditText

    // Variables de apoyo
    private lateinit var usuario: String
    private lateinit var clave: String

    // Validando correo y clave
    private var emailValido = "mail@gmail.com"
    private var claveValida = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializar()
        verificarLogin()
    }

    private fun verificarLogin() {
        if(preferencias.isLogin()!!){
            val bienvenidoIntent = Intent(this,Bienvenido::class.java)
            startActivity(bienvenidoIntent)
            finish()
        }
    }

    private fun inicializar() {
        preferencias = DarkPreferenceManager(this)
        correoElectronico = findViewById(R.id.txtEmail)
        claveUsuario = findViewById(R.id.txtClave)
    }

    fun iniciarSesion(view: View){
        usuario = correoElectronico.text.toString().trim()
        clave = claveUsuario.text.toString().trim()
        if(usuario.isEmpty() || usuario == ""){
            correoElectronico.error = "Correo electrónico requerido "
            correoElectronico.requestFocus()
        } else if (clave.isEmpty() || clave == ""){
            claveUsuario.error = "Clave requerida "
            claveUsuario.requestFocus()
        } else if (usuario != emailValido){
            correoElectronico.error = "Correo electrónico invalido "
            correoElectronico.requestFocus()
        } else if (clave != claveValida) {
            claveUsuario.error = "Clave invalida "
            claveUsuario.requestFocus()
        } else {
            preferencias.setLogin(true)
            preferencias.setNombreDeUsuario(usuario)

            val bienvenidoIntent = Intent(this, Bienvenido::class.java)
            startActivity(bienvenidoIntent)
            finish()
        }
    }
}


