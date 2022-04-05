package com.example.e_shop.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        adapter = CartAdapter(
            onModify = {
                cartViewModel.modifyCart(it.quantity, it.uid)
            },
            onDelete = {
                cartViewModel.removeFromCart(it)
            }
        )

        binding.cartRecyclerView.adapter = adapter
        observeData()

        return binding.root
    }

    private fun observeData() {
        cartViewModel.cartList.observe(viewLifecycleOwner){
            adapter.submitData(it)
            var total = 0.0
            it.forEach { cart ->
                total += cart.quantity * cart.product.price
            }
            binding.cartTotalTextView.text = total.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}