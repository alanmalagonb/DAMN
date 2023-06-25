package com.ipn.demodb

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FraseAdapter(private val datalist: List<Frase>) : RecyclerView.Adapter<FraseAdapter.ManejadorVista>() {


    class ManejadorVista(elementoVista: View):
        RecyclerView.ViewHolder(elementoVista){
        var frase: TextView
        var autor: TextView
        init{
            frase = elementoVista.findViewById(R.id.frase)
            autor = elementoVista.findViewById(R.id.autor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManejadorVista {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item_layout, parent, false)
        return ManejadorVista(vista)
    }

    override fun getItemCount(): Int {
        // crear un arreglo de elementos a mostrar
        // return elArreglo size
        return datalist.size
    }

    override fun onBindViewHolder(holder: ManejadorVista, position: Int) {
        val frase = datalist[position]
        holder.frase.text = frase.frase
        holder.autor.text = frase.autor
    }

}
