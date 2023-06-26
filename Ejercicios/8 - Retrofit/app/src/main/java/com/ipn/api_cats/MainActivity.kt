package com.ipn.api_cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipn.api_cats.adaptador.GatoAdapter
import com.ipn.api_cats.modelo.GatosResponse
import com.ipn.api_cats.servicio.APIService
import com.ipn.api_rickandmorty.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GatoAdapter
    private val imagenesDeGatos = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svGatos.setOnQueryTextListener(this)
        iniciarRecyclerView()
    }

    fun obtenerRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buscarPorNombre(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<List<GatosResponse>> = obtenerRetrofit()
                .create(APIService::class.java)
                .obtenerGatosPorRazas("search?limit=25&breed_ids=$query&api_key=live_Z7b28jk1m38u0te0tPHxHfwcqM11dqSEPKIX2KmitFH2Q5yJmXQMBglTGVkjOej0")

            runOnUiThread(){
                if(call.isSuccessful){
                    // mostrar recycler View
                    val gatitos: List<GatosResponse>? = call.body()
                    val imagenes: List<String> = gatitos?.mapNotNull { it.url } ?: emptyList()
                    imagenesDeGatos.clear()
                    imagenesDeGatos.addAll(imagenes)
                    print("uwu"+imagenes.toString())
                    adapter.notifyDataSetChanged()
                }else{
                    //mostrar errores
                    mostrarErrores()
                }
                ocultarTeclado()
            }

        }
    }

    private fun mostrarErrores(){
        Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            buscarPorNombre(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun iniciarRecyclerView() {
        adapter = GatoAdapter(imagenesDeGatos)
        binding.rvGatos.layoutManager = LinearLayoutManager(this)
        binding.rvGatos.adapter = adapter
    }

    private fun ocultarTeclado(){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.vistaPrincipal.windowToken,0)
    }
}