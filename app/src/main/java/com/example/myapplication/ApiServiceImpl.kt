package com.example.myapplication

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiServiceImpl(
    private val apiService: ApiService
) {

    fun getPlanets(callback: (List<Planet>?) -> Unit) {
        apiService.getPlanets().enqueue(object : Callback<List<Planet>> {
            override fun onResponse(call: Call<List<Planet>>, response: Response<List<Planet>>) {
                return if (response.isSuccessful) {
                    Log.e(TAG, "${response.body()}")
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Planet>>, t: Throwable) {
                Log.e(TAG, "Network error: ${t.message}")
            }
        })
    }
}