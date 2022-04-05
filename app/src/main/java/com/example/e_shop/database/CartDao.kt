package com.example.e_shop.database

import androidx.room.*
import com.example.e_shop.models.Cart
import com.example.e_shop.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getAllCart(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cart: Cart)

    @Delete
    suspend fun removeFromCart(cart: Cart)

    // Update quantity in cart
    @Query("UPDATE cart SET quantity = :newQuantity WHERE uid = :uid")
    suspend fun modifyCart(newQuantity: Int, uid: Int)
}