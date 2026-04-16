package com.programa1.horoscopo_android.utils

import  android.R
import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context){

    companion object{
        const val FAVORITE_HOROSCOPE="Favorite_Horoscope"
    }

    var sharedPref: SharedPreferences = context.getSharedPreferences("Horoscope_session", Context.MODE_PRIVATE)

    fun setFavoriteHoroscope(id: String){
        val editor = sharedPref.edit()
        editor.putString(FAVORITE_HOROSCOPE, id)
        editor.apply()
    }

    fun getFavoriteHoroscope(): String{
        return sharedPref.getString(FAVORITE_HOROSCOPE, "")!!
    }

    fun isFavoriteHoroscope(id: String): Boolean{
        return getFavoriteHoroscope()==id
    }


}