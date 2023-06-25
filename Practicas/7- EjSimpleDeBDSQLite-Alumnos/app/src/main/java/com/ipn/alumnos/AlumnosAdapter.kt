package com.ipn.alumnos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlumnosAdapter : RecyclerView.Adapter<AlumnosAdapter.AlumnoViewHolder>() {

    private var listaAlumnos: List<AlumnoDTO> = emptyList()

    inner class AlumnoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvApellido: TextView = itemView.findViewById(R.id.tvApellido)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_alumno, parent, false)
        return AlumnoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val alumno = listaAlumnos[position].alumno

        holder.tvNombre.text = alumno.nombreAlumno
        holder.tvApellido.text = "${alumno.paternoAlumno} ${alumno.maternoAlumno}"
        holder.tvEmail.text = alumno.email
        holder.tvFecha.text = alumno.fechaCreacion.toString()
    }

    override fun getItemCount(): Int {
        return listaAlumnos.size
    }

    fun actualizarAlumnos(alumnos: List<AlumnoDTO>) {
        listaAlumnos = alumnos
        notifyDataSetChanged()
    }
}
