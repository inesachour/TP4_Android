package com.gl4.tp5mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel {
    private val weatherReponse = MutableLiveData<List<WeatherResponse>>()
    var weather : LiveData<List<WeatherResponse>> = weatherReponse

    fun getForecast(city : String){
        RetrofitHelper.retrofitService.getForecast(city).enqueue(
            object : Callback<List<WeatherResponse>> {
                override fun onResponse(
                    call: Call<List<WeatherResponse>>,
                    response: Response<List<WeatherResponse>>
                ) {
                    if(response.isSuccessful){
                        weatherReponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<WeatherResponse>>, t: Throwable) {

                }

            }
        )
    }

}