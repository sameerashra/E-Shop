package com.example.e_shop.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Product(
    @Json(name = "_id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val category: String,
    val price: Double
)
