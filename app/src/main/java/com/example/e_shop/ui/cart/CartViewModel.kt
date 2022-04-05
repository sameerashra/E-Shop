package com.example.e_shop.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_shop.database.CartDao
import com.example.e_shop.models.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartDao: CartDao
): ViewModel() {

    val cartList = cartDao.getAllCart().asLiveData()

    fun removeFromCart(cart: Cart){
        viewModelScope.launch {
            cartDao.removeFromCart(cart)
        }
    }

    fun modifyCart(newQuantity: Int, id: Int){
        viewModelScope.launch {
            cartDao.modifyCart(newQuantity, id)
        }
    }
}