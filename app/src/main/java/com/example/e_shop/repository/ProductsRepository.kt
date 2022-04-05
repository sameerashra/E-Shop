package com.example.e_shop.repository

import com.example.e_shop.api.ProductsApi
import com.example.e_shop.database.CartDao
import com.example.e_shop.database.ProductDao
import com.example.e_shop.models.Cart
import com.example.e_shop.models.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productDao: ProductDao,
    private val productsApi: ProductsApi,
    private val cartDao: CartDao
) {

    suspend fun getAllRemoteProducts(): Flow<List<Product>> {
        try {
            // Fetch data from the backend
            val response = productsApi.getAllProducts()
            return if (response.isSuccessful && response.body() != null) {
                // if response is successfull store data locally
                response.body()!!.forEach { product ->
                    productDao.insertProduct(product)
                }
                productDao.getAllProducts()
            } else {
                // if backend is not available fetch from local storage
                productDao.getAllProducts()
            }
        } catch (e: Exception) {
            return productDao.getAllProducts()
        }
    }

    fun getAllCart(): Flow<List<Cart>> {
        return cartDao.getAllCart()
    }

    suspend fun addToCart(cart: Cart) {
        cartDao.addToCart(cart)
    }
}