package com.ipn.eventos.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipn.eventos.data.db.entities.Evento

/**
 * Objeto de Acceso a Datos (DAO, por sus siglas en inglés) para la entidad 'Evento'.
 * Esta interfaz proporciona métodos para interactuar con la tabla 'Evento' en la base de datos.
 */
@Dao
interface EventoDAO {
    /**
     * Inserta un nuevo registro de 'Evento' en la base de datos.
     * @param evento El objeto 'Evento' a insertar.
     */
    @Insert
    fun insert(evento: Evento)

    /**
     * Actualiza un registro existente de 'Evento' en la base de datos.
     * @param evento El objeto 'Evento' actualizado.
     */
    @Update
    fun update(evento: Evento)

    /**
     * Elimina un registro de 'Evento' de la base de datos.
     * @param evento El objeto 'Evento' a eliminar.
     */
    @Delete
    fun delete(evento: Evento)

    /**
     * Recupera todos los registros de 'Evento' de la base de datos.
     * @return Una lista de todos los registros de 'Evento'.
     */
    @Query("SELECT * FROM Evento")
    fun readAll(): List<Evento>

    /**
     * Recupera todos los registros de 'Evento' de la base de datos como LiveData.
     * LiveData es una clase contenedora de datos que puede ser observada para detectar cambios.
     * @return Un objeto LiveData que contiene una lista de todos los registros de 'Evento'.
     */
    @Query("SELECT * FROM Evento")
    fun getAllUsersInDB(): LiveData<List<Evento>>

    /**
     * Recupera un registro de 'Evento' de la base de datos según su ID.
     * @param idEvento El ID del registro de 'Evento' a recuperar.
     * @return El registro de 'Evento' con el ID especificado.
     */
    @Query("SELECT * FROM Evento WHERE idEvento LIKE :idEvento")
    fun read(idEvento: Int): Evento
}
