package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Planet(
    val name: String?,
    val rotationPeriod: Int? = 0,
    val orbitalPeriod: Int? = 0,
    val diameter: Int? = 0,
    val climate: String?,
    val gravity: String?,
    val terrain: String,
    val surfaceWater: String,
    val population: String,
    val residents: String,
    val films: String,
    val created: String,
    val edited: String,
    @PrimaryKey val url: String
)
