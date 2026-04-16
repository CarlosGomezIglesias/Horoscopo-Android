package com.programa1.horoscopo_android.adapters

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.programa1.horoscopo_android.data.Horoscope
import com.programa1.horoscopo_android.R
import com.programa1.horoscopo_android.utils.SessionManager


class HoroscopeAdapter (var items: List<Horoscope>, val onItemCLick:(Int) -> Unit): RecyclerView.Adapter <HoroscopeViewHolder> (){
    private var query: String = ""

    //cual es la vista para los elementos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    //cuales son los datos a mostrar para el elemento en la posicion
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = items[position]
        holder.render(horoscope, query)
        holder.itemView.setOnClickListener {
            onItemCLick(position)
        }
    }

 //cuantos elementos tengo que mostrar
    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData (dataSet: List<Horoscope>, query: String){
        items=dataSet
        this.query=query
        notifyDataSetChanged()
    }

}

class HoroscopeViewHolder (view:View): RecyclerView.ViewHolder(view) {
    val signImageView: ImageView = view.findViewById(R.id.signImageView)
    val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val datesTextView: TextView = view.findViewById(R.id.datesTextView)
    val favoriteImageView: ImageView = view.findViewById(R.id.favoriteImageView)

    fun render (horoscope: Horoscope, query: String){
        val context = itemView.context

        val nameText = context.getString(horoscope.name)
        val datesText = context.getString(horoscope.dates)

        nameTextView.text = highlightText(nameText, query)
        datesTextView.text = highlightText(datesText, query)


        signImageView.setImageResource(horoscope.image)

        if(SessionManager(itemView.context).isFavoriteHoroscope(horoscope.id)){
            favoriteImageView.visibility= View.VISIBLE
        }else {
            favoriteImageView.isVisible=false
        }
    }

    fun highlightText(fullText: String, query: String): SpannableString {
        val spannable = SpannableString(fullText)

        if (query.isEmpty()) return spannable

        val lowerText = fullText.lowercase()
        val lowerQuery = query.lowercase()

        var startIndex = 0

        while (true) {
            startIndex = lowerText.indexOf(lowerQuery, startIndex)
            if (startIndex == -1) break

            spannable.setSpan(
                BackgroundColorSpan(Color.GRAY),
                startIndex,
                startIndex + query.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            startIndex += query.length
        }

        return spannable
    }
}