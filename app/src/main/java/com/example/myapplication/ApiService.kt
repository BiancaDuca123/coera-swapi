package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

fun interface ApiService {

    @GET("planets")
    fun getPlanets(): Call<List<Planet>>
}