package com.ipn.demodb

import android.app.AlertDialog
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var frase: EditText
    lateinit var autor: EditText
    lateinit var btnGuardar: Button
    lateinit var btnListado: Button
    lateinit var db: SQLiteHelper
    lateinit var texto: String
    lateinit var tvListado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frase = findViewById(R.id.txtFrase)
        autor = findViewById(R.id.txtAutor)

        tvListado = findViewById(R.id.tvListado)

        btnGuardar = findViewById(R.id.btnAceptar)
        btnListado = findViewById(R.id.btnListado)
        texto = ""

        db = SQLiteHelper(this)

        btnGuardar.setOnClickListener() {
            val laFrase = frase.text.toString()
            val elAutor = autor.text.toString()

            val f = Frase(laFrase, elAutor)

            val almacenado = db.create(f)

            if (almacenado == true) {
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                frase.text.clear()
                autor.text.clear()
            }
        }

        btnListado.setOnClickListener(){
            Toast.makeText(this, "Listado", Toast.LENGTH_SHORT).show()

            val cursor = db.readAll()
            val sb = StringBuilder()
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        sb.append("frase : ").append(cursor.getString(1))
                        sb.append(", autor : ").append(cursor.getString(2)).append("\n")
                    }while (cursor.moveToNext())
                }
            }
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("lista")
            alertDialog.setMessage(sb.toString())
            alertDialog.show()

            texto=""
            muestraTabla()
            tvListado.text = texto

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

            val cursorA = db.readAll()
            val adapter = FraseAdapter(getFrasesFromCursor(cursorA))

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }
    }

    fun muestraTabla(){
        val cursor = db.readAll()
        val numeroFilas : Int = cursor.count
        cursor.moveToFirst()
        for (i in 1 .. numeroFilas){
            var id : Int = cursor.getInt(0)
            var frase : String = cursor.getString(1)
            var autor : String = cursor.getString(2)
            texto = texto +"\n"+id+", "+frase+", "+autor+"\n"
            cursor.moveToNext()
        }
    }

    fun getFrasesFromCursor(cursor: Cursor): List<Frase> {
        val frasesList = mutableListOf<Frase>()
        while (cursor.moveToNext()) {
            val frase = cursor.getString(1)
            val autor = cursor.getString(2)
            val frasea = Frase(frase, autor)
            frasesList.add(frasea)
        }
        return frasesList
    }
}