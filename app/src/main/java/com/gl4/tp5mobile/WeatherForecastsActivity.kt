package com.gl4.tp5mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.tp5mobile.databinding.ActivityMainBinding
import com.gl4.tp5mobile.databinding.ActivityWeatherForecastsBinding

class WeatherForecastsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherForecastsBinding
    var forecastViewModel : ForecastViewModel = ForecastViewModel()
    var data : List<WeatherResponse> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherForecastsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val city=intent.getStringExtra("city")
        if(city != null){
            forecastViewModel.getForecast(city)

        }

        forecastViewModel.weather.observe(this) {
            if (it != null)
                data = it
                binding.forecastsRecycler.adapter = WeatherAdapter(data)
        }

        binding.forecastsRecycler.apply {
            layoutManager = LinearLayoutManager(this@WeatherForecastsActivity)
            adapter = WeatherAdapter(data)
        }
    }
}