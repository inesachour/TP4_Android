package com.gl4.tp5mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp5mobile.forecast_models.ForecastResponse
import com.gl4.tp5mobile.weather_models.WeatherResponse

class WeatherAdapter(private val forecast : ForecastResponse?) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView
        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = forecast!!.list[position].pressure.toString()
    }

    override fun getItemCount(): Int {
        if(forecast != null) return forecast.list.size
        return 0
    }
}