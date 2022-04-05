package com.example.e_shop.ui.products

import androidx.lifecycle.*
import com.example.e_shop.models.Cart
import com.example.e_shop.models.Product
import com.example.e_shop.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
): ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    init {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getAllRemoteProducts().collect {
                withContext(Dispatchers.Main){
                    _products.value = it
                }
            }
        }
    }


    fun addItemToCart(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.addToCart(Cart(
                uid = 0,
                quantity = 1,
                product = product
            ))
        }
    }
}