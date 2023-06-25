package com.ipn.listaarticulos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lista=findViewById<ListView>(R.id.lista)
        var adaptadorDatosArticulo = AdaptadorArticulo(this, generarDatosFicticios())
        lista?.adapter = adaptadorDatosArticulo
        adaptadorDatosArticulo?.notifyDataSetChanged()
    }// fin onCreate

    private fun generarDatosFicticios(): ArrayList<Articulo> {
        var listaDeArticulos = ArrayList<Articulo>()
        val a1:Articulo = Articulo(
            "Fresa",500.0,"Fresa",R.drawable.fresa
        )
        val a2:Articulo= Articulo(
            "Mango",29.3,"Mango",R.drawable.mango
        )
        val a3:Articulo= Articulo(
            "Kiwi",60.5,"Kiwi",R.drawable.kiwi
        )
        val a4:Articulo= Articulo(
            "Sandia",120.34,"Sandia",R.drawable.sandia
        )
        val a5:Articulo= Articulo(
            "Naranja",10.5,"Naranja",R.drawable.naranja
        )

        listaDeArticulos.add(a1)
        listaDeArticulos.add(a2)
        listaDeArticulos.add(a3)
        listaDeArticulos.add(a4)
        listaDeArticulos.add(a5)

        return listaDeArticulos
    }
}