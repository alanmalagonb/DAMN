package com.ipn.demodb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context,"FrasesChuck",null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val sql="create table Frases(idFrase integer primary key autoincrement, " +
                "frase text, autor text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists Frases")
        onCreate(db)
    }

    fun create(frase:Frase):Boolean{
        val db = writableDatabase

        val contentValues = ContentValues()
        contentValues.put("frase",frase.frase)
        contentValues.put("autor",frase.autor)
        val resultado :Long = db.insert("Frases",null,contentValues)
        return resultado != -1.toLong()
    }

    fun readAll(): Cursor {
        val db = readableDatabase
        val cursor = db.query("Frases", null, null, null, null, null, null)
        cursor.moveToFirst()
        return cursor
    }
}