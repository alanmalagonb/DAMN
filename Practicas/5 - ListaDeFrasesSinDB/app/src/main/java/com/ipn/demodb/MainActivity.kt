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
    lateinit var texto: String
    lateinit var tvListado: TextView

    var frases = mutableListOf<Frase>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frase = findViewById(R.id.txtFrase)
        autor = findViewById(R.id.txtAutor)

        tvListado = findViewById(R.id.tvListado)

        btnGuardar = findViewById(R.id.btnAceptar)
        btnListado = findViewById(R.id.btnListado)
        texto = ""


        btnGuardar.setOnClickListener() {
            val laFrase = frase.text.toString()
            val elAutor = autor.text.toString()

            val f = Frase(laFrase, elAutor)

            frases.add(f)


                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                frase.text.clear()
                autor.text.clear()

        }

        btnListado.setOnClickListener(){
            Toast.makeText(this, "Listado", Toast.LENGTH_SHORT).show()

            val sb = StringBuilder()

            for(frase in frases){
                sb.append("frase : ").append(frase.frase)
                sb.append(", autor : ").append(frase.autor).append("\n")
            }

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("lista")
            alertDialog.setMessage(sb.toString())
            alertDialog.show()

            texto=""
            muestraTabla()
            tvListado.text = texto

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

            val adapter = FraseAdapter(frases)

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }
    }

    fun muestraTabla(){
        frases.forEachIndexed{index,item->

            var id : Int = index+1
            var frase : String = item.frase
            var autor : String = item.autor
            texto = texto +"\n"+id+", "+frase+", "+autor+"\n"
        }
    }

}