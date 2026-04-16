package com.programa1.horoscopo_android.utils

import  android.R
import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context){

    var sharedPref: SharedPreferences = context.getSharedPreferences("horoscope_session", Context.MODE_PRIVATE)

    fun setFavoriteHoroscope(id: String){
        val editor = sharedPref.edit()
        editor.putString("Favorite_Horoscope", id)
        editor.apply()
    }

    fun getFavoriteHoroscope(): String{
        return sharedPref.getString("Favorite_Horoscope", "")!!
    }

    fun isFavoriteHoroscope(id: String): Boolean{
        return getFavoriteHoroscope()==id
    }


}