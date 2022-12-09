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
        val pressure : TextView
        val temperature : TextView
        init {
            pressure = itemView.findViewById(R.id.pressure)
            temperature = itemView.findViewById(R.id.temperature)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pressure.text = forecast!!.list[position].pressure.toString()
        holder.temperature.text = forecast!!.list[position].temp.day.toString()
    }

    override fun getItemCount(): Int {
        if(forecast != null) return forecast.list.size
        return 0
    }
}