package com.ipn.api_cats.adaptador

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ipn.api_rickandmorty.databinding.ElementoGatoBinding
import com.squareup.picasso.Picasso

class GatosViewholder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = ElementoGatoBinding.bind(view)

    fun bind(imagen: String){
        Picasso.get().load(imagen).into(binding.ivGato)
    }
}