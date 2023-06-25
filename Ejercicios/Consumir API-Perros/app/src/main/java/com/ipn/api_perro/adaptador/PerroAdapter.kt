package com.ipn.api_perro.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipn.api_perro.R

class PerroAdapter(val imagenes:List<String>): RecyclerView.Adapter<PerrosViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerrosViewholder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        return PerrosViewholder(layoutInflater.inflate(R.layout.elemento_perro,parent,false))
    }

    override fun getItemCount(): Int {
        return imagenes.size
    }

    override fun onBindViewHolder(holder: PerrosViewholder, position: Int) {
        val elemento: String = imagenes[position]
        holder.bind(elemento)
    }
}