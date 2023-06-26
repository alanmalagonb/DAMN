package com.ipn.eventos.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * Clase que representa la entidad 'Evento' en la base de datos.
 * Esta clase define la estructura y los atributos de un evento.
 *
 * @param idEvento El ID del evento (generado automáticamente).
 * @param nombreEvento El nombre del evento.
 * @param descripcionEvento La descripción del evento.
 * @param fechaEvento La fecha del evento.
 */
@Entity(tableName = "Evento")
data class Evento(
    @PrimaryKey(autoGenerate = true)
    val idEvento: Int,
    @ColumnInfo(name = "nombreEvento")
    var nombreEvento: String,
    @ColumnInfo(name = "descripcionEvento")
    var descripcionEvento: String,
    @ColumnInfo(name = "fechaEvento")
    var fechaEvento: Date
)
