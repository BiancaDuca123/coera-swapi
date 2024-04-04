package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var planetAdapter: PlanetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() {
        val localDatabase = Room.databaseBuilder(
            applicationContext,
            PlanetDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        val retrofitInstance = Retrofit.Builder()
            .baseUrl("http://swapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val planetDao = localDatabase.planetDao()

        val planets: LiveData<List<Planet>> = planetDao.getAll()

        val api = ApiServiceImpl(
            retrofitInstance.create(ApiService::class.java)
        )

        planetAdapter = PlanetAdapter()

        binding.recyclerView.run{
            layoutManager = LinearLayoutManager(context)
            adapter = planetAdapter
        }

        planets.observe(this) {
            planetAdapter.submitList(it)
        }

        api.getPlanets {
            Log.d("Activity", "$it")
            it?.forEach { planet ->
                planetDao.insertAll(planet)
            }
        }
    }
}