package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlanetDao {
    @Insert
    fun insertAll(vararg planets: Planet)

    @Delete
    fun delete(planet: Planet)

    @Query("SELECT * FROM planet")
    fun getAll(): LiveData<List<Planet>>

}