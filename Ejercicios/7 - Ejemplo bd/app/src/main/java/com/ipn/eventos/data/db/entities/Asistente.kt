package com.ipn.eventos.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Clase que representa la entidad 'Asistente' en la base de datos.
 * Esta clase define la estructura y los atributos de un asistente de un evento.
 *
 * @param idAsistente El ID del asistente (generado automáticamente).
 * @param nombreAsistente El nombre del asistente.
 * @param paternoAsistente El apellido paterno del asistente.
 * @param maternoAsistente El apellido materno del asistente.
 * @param emailAsistente El correo electrónico del asistente.
 * @param idEvento El ID del evento al que pertenece el asistente.
 */
@Entity(tableName = "Asistente")
data class Asistente(
    @PrimaryKey(autoGenerate = true)
    val idAsistente: Int,
    @ColumnInfo(name = "nombreAsistente")
    var nombreAsistente: String,
    @ColumnInfo(name = "paternoAsistente")
    var paternoAsistente: String,
    @ColumnInfo(name = "maternoAsistente")
    var maternoAsistente: String,
    @ColumnInfo(name = "emailAsistente")
    var emailAsistente: String,
    @ColumnInfo(name = "idEvento")
    val idEvento: Int
)
