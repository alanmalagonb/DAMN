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
import com.ipn.eventos.data.db.entities.Evento
import com.ipn.eventos.ui.activities.AsistenteActivity
import com.ipn.eventos.ui.activities.AsistentesActivity
import com.ipn.eventos.ui.activities.EventoActivity

/**
 * Clase adaptador para el RecyclerView que muestra la lista de eventos.
 * Esta clase se encarga de gestionar la vista de cada elemento de la lista y de manejar las interacciones con los botones.
 *
 * @param context El contexto de la aplicación.
 * @param eventosDatabase La instancia de la base de datos de eventos.
 */
class EventosAdapter(private val context: Context, private val eventosDatabase: EventosDatabase) :
    RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

    private val eventos: MutableList<Evento> = eventosDatabase.eventoDao().readAll() as MutableList<Evento>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.evento_item, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]

        holder.bind(evento)

        holder.buttonEliminar.setOnClickListener {
            Toast.makeText(context, "ID: ${evento.idEvento}", Toast.LENGTH_LONG).show()

            // Realizar la lógica para eliminar el evento de la base de datos
            eventosDatabase.eventoDao().delete(evento)

            // Actualizar el contenido del adaptador eliminando el evento de la lista
            eventos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, eventos.size)
            actualizarEventos()
        }

        holder.buttonModificar.setOnClickListener {
            Toast.makeText(context, "ID: ${evento.idEvento}", Toast.LENGTH_LONG).show()

            // Abrir la actividad de modificación de eventos
            val intent = Intent(context, EventoActivity::class.java)
            intent.putExtra("evento_id", evento.idEvento)
            context.startActivity(intent)
        }

        holder.buttonAsistentes.setOnClickListener {
            Toast.makeText(context, "ID: ${evento.idEvento}", Toast.LENGTH_LONG).show()

            // Abrir la actividad de asistentes para el evento
            val intent = Intent(context, AsistentesActivity::class.java)
            intent.putExtra("evento_id", evento.idEvento)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    inner class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val buttonEliminar: Button = itemView.findViewById(R.id.buttonEliminar)
        val buttonModificar: Button = itemView.findViewById(R.id.buttonModificar)
        val buttonAsistentes: Button = itemView.findViewById(R.id.buttonAsistentes)

        /**
         * Vincula los datos del evento a la vista del ViewHolder.
         */
        fun bind(evento: Evento) {
            textViewNombre.text = evento.nombreEvento
        }
    }

    /**
     * Actualiza la lista de eventos en el adaptador.
     * Esta función se utiliza después de realizar cambios en la base de datos para reflejar los cambios en la vista.
     */
    fun actualizarEventos() {
        eventos.clear()
        eventos.addAll(eventosDatabase.eventoDao().readAll() as MutableList<Evento>)
        notifyDataSetChanged()
    }
}
