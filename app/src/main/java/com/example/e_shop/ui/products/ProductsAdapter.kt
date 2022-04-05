package com.example.e_shop.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_shop.R
import com.example.e_shop.databinding.ProductListItemBinding
import com.example.e_shop.models.Product
import com.example.e_shop.utils.DiffUtilHelper

class ProductsAdapter(
    private val onClick: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    var productList: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(productList[position], position, onClick)
    }

    override fun getItemCount(): Int = productList.size

    fun submitList(newProductList: List<Product>) {
        val productDiffUtil = DiffUtilHelper(productList, newProductList)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        productList = newProductList
        productDiffUtilResult.dispatchUpdatesTo(this)
    }

    class ProductsViewHolder(val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, position: Int, onClick: (product: Product) -> Unit) {
            binding.productListItemImageView.setImageResource(
                if (position % 2 == 0)
                    R.drawable.image_1
                else
                    R.drawable.image_2
            )
            binding.productListItemContainer.setOnClickListener {
                onClick(product)
            }
            binding.product = product
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListItemBinding.inflate(layoutInflater, parent, false)
                return ProductsViewHolder(binding)
            }
        }

    }
}