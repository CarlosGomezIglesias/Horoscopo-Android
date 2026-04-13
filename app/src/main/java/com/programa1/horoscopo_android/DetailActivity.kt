package com.programa1.horoscopo_android

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val horoscope = Horoscope.getById(id)!!
        Log.i("ZODIAC", "${getString(horoscope.name)} -> ${getString(horoscope.dates)}")

        //Codigo intentado por mi//

        findViewById<TextView>(R.id.nombreClick).text = getString(horoscope.name)
        findViewById<TextView>(R.id.fechasClick).text  = getString(horoscope.dates)
        findViewById<ImageView>(R.id.imageClick).setImageResource(horoscope.image)





    }
}