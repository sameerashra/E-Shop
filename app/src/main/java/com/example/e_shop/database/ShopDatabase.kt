package com.example.e_shop.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_shop.models.Cart
import com.example.e_shop.models.Product

@Database(
    entities = [Product::class, Cart::class],
    version = 2
)
abstract class ShopDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}