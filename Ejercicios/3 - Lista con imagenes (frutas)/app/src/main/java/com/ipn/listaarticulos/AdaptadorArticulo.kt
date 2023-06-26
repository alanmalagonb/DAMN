package com.ipn.listaarticulos

import android.app.Activity
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast

class AdaptadorArticulo(private var activity: Activity,
private var items: ArrayList<Articulo>): BaseAdapter(){
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vistaACargos:View?
        val vista: VistaController
        if(convertView == null){
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vistaACargos = inflater.inflate(R.layout.elemento_articulo,null)
            vista = VistaController(vistaACargos)
            vistaACargos?.tag = vista
        }else{
            vistaACargos = convertView
            vista = vistaACargos.tag as VistaController
        }
        var articulos = items[position]
        vista.imgArticulo?.setImageResource(articulos.imagenArticulo)
        vista.txtNombre?.text=articulos.nombreArticulo
        vista.txtDetalle?.text=articulos.descripcionArticulo
        vista.txtPrecio?.text= articulos.precioArticulo.toString()

        vistaACargos?.setOnClickListener(){
            Toast.makeText(activity,"Articulo Seleccionado: ${articulos.nombreArticulo}",
            Toast.LENGTH_SHORT).show()
        }
        return vistaACargos as View
    }
}
