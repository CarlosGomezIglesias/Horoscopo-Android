package com.programa1.horoscopo_android.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.programa1.horoscopo_android.data.Horoscope
import com.programa1.horoscopo_android.adapters.HoroscopeAdapter
import com.programa1.horoscopo_android.R
import com.programa1.horoscopo_android.utils.search

class MainActivity : AppCompatActivity() {
    var horoscopeList: List<Horoscope> = Horoscope.horoscopeList

    /*codigo mio
    var listaFiltrada = horoscopeList.toMutableList()

    */
    lateinit var recyclerView: RecyclerView

    lateinit var adapter: HoroscopeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        recyclerView = findViewById(R.id.recyclerView)

        adapter = HoroscopeAdapter(horoscopeList, { position ->
            val horoscope = horoscopeList[position]
            //Navegar al detalle
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Horoscope_id", horoscope.id)
            startActivity(intent)
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(horoscopeList, query ="" )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)

        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("Zodiac", "Buscando: $query")

                //Codigo mio// manda un mensaje de buscando: "el texto que has escrito"
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                intent.putExtra("query", query)
                Toast.makeText(this@MainActivity, "Buscando: $query", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                //
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                horoscopeList = Horoscope.horoscopeList.filter {
                    getString(it.name).search(newText)
                            || getString(it.dates).search(newText)
                }
                adapter.updateData(horoscopeList, newText)

                return true
            }

            /* codigo mio
            val texto = newText?.lowercase() ?: ""

            listaFiltrada.clear()
            fun filtrar(){

            }
            if (texto.isEmpty()) {
                listaFiltrada.addAll(horoscopeList)
            } else {
                horoscopeList.forEach {
                    if (getString(it.name).lowercase().contains(texto)
                        || getString(it.dates).lowercase().contains(texto)) {
                        listaFiltrada.add(it)
                    }
                }
                //
                adapter.items = listaFiltrada
                adapter.notifyDataSetChanged()  */


        })
        return true
    }

}