package com.gl4.tp5mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.gl4.tp5mobile.databinding.ActivityMainBinding
import com.gl4.tp5mobile.weather_models.WeatherResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var weatherViewModel : WeatherViewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.weather.observe(this) {
            if (it != null)
                setWeather(it)
        }

        val cities = listOf<String>("Tunis", "New York", "Paris", "Rome")
        val citiesAdapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, cities)
        val spinner = binding.citiesSpinner
        spinner.adapter = citiesAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                weatherViewModel.changeCity(cities[p2])
                if(weatherViewModel.weather.value != null){
                    setWeather(weatherViewModel.weather.value!!)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        binding.forecastButton.setOnClickListener{
            val intent = Intent(this, WeatherForecastsActivity::class.java)
            intent.putExtra("city", spinner.selectedItem.toString())
            startActivity(intent)
        }
    }

    fun setWeather(weather : WeatherResponse){
        binding.cityTextView.text = weather.name
        binding.temperatureTextView.text = "${weather.main.temp.toString()}°C"
        //icon
        binding.weatherTextView.text = weather.weather[0].description
        binding.humidityTextView.text = "Humidity : ${weather.main.humidity}"
        binding.pressureTextView.text = "Pressure : ${weather.main.pressure}"

    }
}