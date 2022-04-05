package com.example.e_shop.api

import com.example.e_shop.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    // Get all product from backend
    @GET("products/all")
    suspend fun getAllProducts(): Response<List<Product>>
}