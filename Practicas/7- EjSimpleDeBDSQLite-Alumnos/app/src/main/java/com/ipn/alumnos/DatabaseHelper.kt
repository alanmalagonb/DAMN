package com.ipn.alumnos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "clase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_ALUMNO = "Alumno"
        private const val COLUMN_ID = "idAlumno"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_APELLIDO_P = "apellidoP"
        private const val COLUMN_APELLIDO_M = "apellidoM"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_FECHA_CREACION = "fechaCreacion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_ALUMNO ($COLUMN_ID  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOMBRE TEXT, $COLUMN_APELLIDO_P TEXT, $COLUMN_APELLIDO_M TEXT, " +
                "$COLUMN_EMAIL TEXT, $COLUMN_FECHA_CREACION INTEGER)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ALUMNO")
        onCreate(db)
    }

    fun insertAlumno(alumno: Alumno) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NOMBRE, alumno.nombreAlumno)
        values.put(COLUMN_APELLIDO_P, alumno.paternoAlumno)
        values.put(COLUMN_APELLIDO_M, alumno.maternoAlumno)
        values.put(COLUMN_EMAIL, alumno.email)
        values.put(COLUMN_FECHA_CREACION, alumno.fechaCreacion.time)

        db.insert(TABLE_ALUMNO, null, values)
        db.close()
    }

    fun getAllAlumnos(): List<AlumnoDTO> {
        val alumnos = mutableListOf<AlumnoDTO>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_ALUMNO"
        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                    val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
                    val apellidoP = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APELLIDO_P))
                    val apellidoM = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APELLIDO_M))
                    val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
                    val fechaCreacion = Date(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_FECHA_CREACION)))

                    val alumno = Alumno(id, nombre, apellidoP, apellidoM, email, fechaCreacion)
                    val alumnoDTO = AlumnoDTO(alumno)
                    alumnos.add(alumnoDTO)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        db.close()

        return alumnos
    }

}
