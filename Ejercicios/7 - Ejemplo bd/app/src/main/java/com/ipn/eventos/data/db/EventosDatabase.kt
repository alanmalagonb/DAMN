package com.ipn.eventos.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ipn.eventos.data.db.converters.Converters
import com.ipn.eventos.data.db.dao.AsistenteDAO
import com.ipn.eventos.data.db.dao.EventoDAO
import com.ipn.eventos.data.db.entities.Asistente
import com.ipn.eventos.data.db.entities.Evento

/**
 * Clase abstracta que representa la base de datos principal de la aplicación.
 * Esta clase extiende RoomDatabase y define los métodos de acceso a los DAOs correspondientes.
 *
 * @property eventoDao El DAO para acceder a la tabla de eventos.
 * @property asistenteDao El DAO para acceder a la tabla de asistentes.
 */
@Database(entities = [Evento::class, Asistente::class], version = 2)
@TypeConverters(Converters::class)
abstract class EventosDatabase : RoomDatabase() {
    abstract fun eventoDao(): EventoDAO
    abstract fun asistenteDao(): AsistenteDAO

    companion object {
        @Volatile
        private var INSTANCE: EventosDatabase? = null

        /**
         * Obtiene una instancia de la base de datos.
         * Si no existe una instancia previa, se crea una nueva.
         *
         * @param context El contexto de la aplicación.
         * @return La instancia de la base de datos.
         */
        fun getInstance(context: Context): EventosDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EventosDatabase::class.java,
                        "EventosAndroid"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}
