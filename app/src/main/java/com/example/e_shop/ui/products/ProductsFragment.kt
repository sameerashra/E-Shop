package com.example.e_shop.ui.products

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentProductsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val productsViewModel: ProductsViewModel by viewModels()
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductsBinding.inflate(inflater, container, false)

        adapter = ProductsAdapter(
            onClick = {
                productsViewModel.addItemToCart(it)
                Snackbar.make(
                    requireContext(),
                    binding.productRecyclerView,
                    "Item Added",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        )
        binding.productRecyclerView.adapter = adapter

        observeData()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun observeData() {
        productsViewModel.products.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.my_cart_menu -> {
                val action = ProductsFragmentDirections.actionProductsFragmentToCartFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}