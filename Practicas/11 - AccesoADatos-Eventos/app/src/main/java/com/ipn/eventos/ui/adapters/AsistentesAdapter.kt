package com.ipn.eventos.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ipn.eventos.R
import com.ipn.eventos.data.db.EventosDatabase
import com.ipn.eventos.data.db.entities.Asistente
import com.ipn.eventos.ui.activities.AsistenteActivity
import com.ipn.eventos.ui.activities.EventoActivity

/**
 * Adaptador para mostrar y gestionar la lista de asistentes de un evento.
 * Permite al usuario eliminar asistentes y abrir la actividad de modificación de asistentes.
 */
class AsistentesAdapter(
    private val context: Context,
    private val eventosDatabase: EventosDatabase,
    private val idEvento: Int
) : RecyclerView.Adapter<AsistentesAdapter.AsistenteViewHolder>() {

    private val asistentes: MutableList<Asistente> =
        eventosDatabase.asistenteDao().getAsistentesByEventoId(idEvento) as MutableList<Asistente>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsistenteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.asistente_item, parent, false)
        return AsistenteViewHolder(view)
    }

    override fun onBindViewHolder(holder: AsistenteViewHolder, position: Int) {
        val asistente = asistentes[position]

        holder.bind(asistente)

        holder.buttonEliminar.setOnClickListener {
            Toast.makeText(context, "ID: ${asistente.idEvento}", Toast.LENGTH_LONG).show()

            // Realizar la lógica para eliminar el asistente de la base de datos
            eventosDatabase.asistenteDao().delete(asistente)

            // Actualizar el contenido del adaptador eliminando el asistente de la lista
            asistentes.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, asistentes.size)
            actualizarAsistentes()
        }

        holder.buttonModificar.setOnClickListener {
            Toast.makeText(context, "ID: ${asistente.idAsistente}", Toast.LENGTH_LONG).show()
            val intent = Intent(context, AsistenteActivity::class.java)
            intent.putExtra("asistente_id", asistente.idAsistente)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return asistentes.size
    }

    inner class AsistenteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val buttonEliminar: Button = itemView.findViewById(R.id.buttonEliminar)
        val buttonModificar: Button = itemView.findViewById(R.id.buttonModificar)

        fun bind(asistente: Asistente) {
            textViewNombre.text = "${asistente.nombreAsistente} ${asistente.paternoAsistente} ${asistente.maternoAsistente}"
        }
    }

    /**
     * Actualiza la lista de asistentes del adaptador.
     */
    fun actualizarAsistentes() {
        asistentes.clear()
        asistentes.addAll(eventosDatabase.asistenteDao().getAsistentesByEventoId(idEvento) as MutableList<Asistente>)
        notifyDataSetChanged()
    }
}

