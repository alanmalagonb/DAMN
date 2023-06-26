package com.ipn.api_cats.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipn.api_rickandmorty.R

class GatoAdapter (val imagenes:List<String>): RecyclerView.Adapter<GatosViewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GatosViewholder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        return GatosViewholder(layoutInflater.inflate(R.layout.elemento_gato,parent,false))
    }

    override fun getItemCount(): Int {
        return imagenes.size
    }

    override fun onBindViewHolder(holder: GatosViewholder, position: Int) {
        val elemento: String = imagenes[position]
        holder.bind(elemento)
    }
}