package com.ipn.eventos.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipn.eventos.data.db.entities.Asistente
import com.ipn.eventos.data.db.entities.Evento

/**
 * Data Access Object (DAO) para la entidad 'Asistente'.
 * Esta interfaz proporciona métodos para interactuar con la tabla 'Asistente' en la base de datos.
 */
@Dao
interface AsistenteDAO {
    /**
     * Inserta un nuevo 'Asistente' en la base de datos.
     * @param asistente El objeto 'Asistente' a insertar.
     */
    @Insert
    fun insert(asistente: Asistente)

    /**
     * Actualiza un 'Asistente' existente en la base de datos.
     * @param asistente El objeto 'Asistente' actualizado.
     */
    @Update
    fun update(asistente: Asistente)

    /**
     * Elimina un 'Asistente' de la base de datos.
     * @param asistente El objeto 'Asistente' a eliminar.
     */
    @Delete
    fun delete(asistente: Asistente)

    /**
     * Recupera todos los registros de 'Asistente' de la base de datos.
     * @return Una lista de todos los registros de 'Asistente'.
     */
    @Query("SELECT * FROM Asistente")
    fun readAll(): List<Asistente>

    /**
     * Recupera todos los registros de 'Asistente' de la base de datos como LiveData.
     * LiveData es una clase contenedora de datos que puede ser observada para detectar cambios.
     * @return Un objeto LiveData que contiene una lista de todos los registros de 'Asistente'.
     */
    @Query("SELECT * FROM Asistente")
    fun getAllUsersInDB(): LiveData<List<Asistente>>

    /**
     * Recupera los 'Asistentes' de un evento específico de la base de datos.
     * @param eventoId El ID del evento.
     * @return Una lista de 'Asistentes' que pertenecen al evento con el ID especificado.
     */
    @Query("SELECT * FROM Asistente WHERE idEvento = :eventoId")
    fun getAsistentesByEventoId(eventoId: Int): List<Asistente>

    /**
     * Recupera un registro de 'Asistente' de la base de datos según su ID.
     * @param idAsistente El ID del registro de 'Asistente' a recuperar.
     * @return El registro de 'Asistente' con el ID especificado.
     */
    @Query("SELECT * FROM Asistente WHERE idAsistente LIKE :idAsistente")
    fun read(idAsistente: Int): Asistente
}
