package com.ipn.listadeanimes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimeAdapter: RecyclerView.Adapter<AnimeAdapter.ManejadorVista>() {

    val listaAnimes = listOf<Anime>(
        Anime("Naruto",R.drawable.naruto),
        Anime("Attack on Titan",R.drawable.eren),
        Anime("Demon Slayer",R.drawable.tanjiro),
        Anime("Sword Art Online",R.drawable.kirito),
        Anime("Sailor Moon",R.drawable.sailormoon))

    class ManejadorVista(elementoVista: View):
        RecyclerView.ViewHolder(elementoVista){
        var imagenAnime: ImageView
        var nombreAnime: TextView
        init{
            imagenAnime = elementoVista.findViewById(R.id.imagenAnime)
            nombreAnime = elementoVista.findViewById(R.id.nombreAnime)
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
        return listaAnimes.size
    }

    override fun onBindViewHolder(holder: ManejadorVista, position: Int) {
        val anime = listaAnimes[position] // Get the Anime object at the given position
        holder.imagenAnime.setImageResource(anime.image) // Set the image resource
        holder.nombreAnime.text = anime.name // Set the name text
    }
}