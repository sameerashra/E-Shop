package com.example.e_shop.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_shop.R
import com.example.e_shop.databinding.CartListItemBinding
import com.example.e_shop.models.Cart
import com.example.e_shop.utils.DiffUtilHelper

class CartAdapter(
    val onModify: (cart: Cart) -> Unit,
    val onDelete: (cart: Cart) -> Unit
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var cartList: List<Cart> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position], position, onModify, onDelete)
    }

    override fun getItemCount(): Int = cartList.size

    fun submitData(newCart: List<Cart>){
        val cartDiffUtil = DiffUtilHelper(cartList, newCart)
        val cartDiffResult = DiffUtil.calculateDiff(cartDiffUtil)
        cartList = newCart
        cartDiffResult.dispatchUpdatesTo(this)
    }

    class CartViewHolder(val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            cart: Cart,
            position: Int,
            onModify: (cart: Cart) -> Unit,
            onDelete: (cart: Cart) -> Unit
        ) {
            binding.imageView.setImageResource(
                if (position % 2 == 0)
                    R.drawable.image_1
                else
                    R.drawable.image_2
            )
            binding.cartItemNameTextView.text = cart.product.name
            binding.cartItemQuantityTextView.text = cart.quantity.toString()
            binding.cartItemPriceTextView.text = (cart.product.price * cart.quantity).toString()

            binding.cartAddImageButton.setOnClickListener {
                onModify(
                    Cart(
                    uid = cart.uid,
                    quantity = cart.quantity + 1,
                    product = cart.product
                )
                )
            }
            binding.cartRemoveImageButton.setOnClickListener {
                if(cart.quantity > 1){
                    onModify(
                        Cart(
                            uid = cart.uid,
                            quantity = cart.quantity - 1,
                            product = cart.product
                        )
                    )
                } else {
                    onDelete(cart)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): CartViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartListItemBinding.inflate(layoutInflater, parent, false)
                return CartViewHolder(binding)
            }
        }

    }
}