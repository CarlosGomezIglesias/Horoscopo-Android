package com.programa1.horoscopo_android.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.programa1.horoscopo_android.data.Horoscope
import com.programa1.horoscopo_android.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getStringExtra("Horoscope_id")!!
        val horoscope = Horoscope.Companion.getById(id)!!
        Log.i("ZODIAC", "${getString(horoscope.name)} -> ${getString(horoscope.dates)}")

        //Codigo intentado por mi//

        findViewById<TextView>(R.id.nombreClick).text = getString(horoscope.name)
        findViewById<TextView>(R.id.fechasClick).text = getString(horoscope.dates)
        findViewById<ImageView>(R.id.imageClick).setImageResource(horoscope.image)

        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.dates)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)






    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_favorite -> {
                //Marcar el horoscopo favorito//
                true
            }
            R.id.menu_share -> {
                //Compartir horoscopo//
                share()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun share (){
        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        sendIntent.setType("text/plain")

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}