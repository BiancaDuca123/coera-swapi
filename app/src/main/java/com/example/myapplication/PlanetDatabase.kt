package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Planet::class], version = 1, exportSchema = false)
abstract class PlanetDatabase: RoomDatabase() {
    abstract fun planetDao(): PlanetDao
}