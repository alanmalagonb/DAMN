package com.ipn.listaarticulos

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class VistaController(renglon: View?) {
    var txtNombre:TextView?=null
    var txtDetalle:TextView?=null
    var imgArticulo:ImageView?=null
    var txtPrecio:TextView?=null
    init {
        this.txtNombre = renglon?.findViewById(R.id.nombre)
        this.txtDetalle = renglon?.findViewById(R.id.txtDescripcion)
        this.imgArticulo = renglon?.findViewById(R.id.imageView)
        this.txtPrecio = renglon?.findViewById(R.id.precio)
    }
}