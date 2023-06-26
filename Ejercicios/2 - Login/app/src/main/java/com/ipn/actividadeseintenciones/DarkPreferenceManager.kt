package com.ipn.actividadeseintenciones

import android.content.Context
import android.content.SharedPreferences

class DarkPreferenceManager(contexto: Context?) {

    // Defnir el modo del Shared Preferences
    val MODO_PRIVADO = 0

    // Nombre del Archivo de Preferencias
    private val PREFERENCE_NAME = "DarkSharedPrefences"
    private val IS_LOGIN = "is_login"

    val preferencias: SharedPreferences? = contexto?.getSharedPreferences(PREFERENCE_NAME,MODO_PRIVADO)
    val editor: SharedPreferences.Editor? = preferencias?.edit()

    fun setLogin(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN,isLogin)
        editor?.commit()
    }

    fun isLogin() : Boolean? {
        return preferencias?.getBoolean(IS_LOGIN, false)
    }

    fun setNombreDeUsuario(nombreDeUsuario: String?) {
        editor?.putString("nombreDeUsuario",nombreDeUsuario)
        editor?.commit()
    }

    fun getNombreDeUsuario() : String?{
        return preferencias?.getString("nombreDeUsuario","")
    }

    fun limpiarPreferencias(){
        editor?.clear()
        editor?.commit()
    }
}
