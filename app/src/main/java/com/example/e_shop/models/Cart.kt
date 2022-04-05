package com.example.e_shop.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    val quantity: Int,

    @Embedded
    val product: Product
)
