package com.example.ebayweatherapp.retrofit.service

import com.example.ebayweatherapp.extensions.autoNetworkThread
import com.example.ebayweatherapp.retrofit.response.ApiResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherService : BaseService {
    @GET("/weather")
    fun getWeatherByLocation(
        @Query("q") location: String
    ): Observable<>

    @GET("/weather")
    fun getWeatherByGPS(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Observable<>

    @GET("api.php")
    fun hitCountCheck(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String
    ): Observable<ApiResponse.Result>
}

class WeatherServiceImpl(retrofit: Retrofit) : WeatherService {
    private val apiService = retrofit.create(WeatherService::class.java)

    override fun hitCountCheck(
        action: String,
        format: String,
        list: String,
        srsearch: String
    ): Observable<ApiResponse.Result> {
        return apiService.hitCountCheck('').autoNetworkThread()
    }
}

