package com.app.kucukbakkalapp.ui.main.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.kucukbakkalapp.R
import com.app.kucukbakkalapp.data.model.products.ProductsData
import com.app.kucukbakkalapp.utils.AddOrExtractInterface
import com.app.kucukbakkalapp.utils.Utils
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment(R.layout.fragment_products), AddOrExtractInterface {
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initClick()
        getProducts()
        listenProductsData()
    }

    private fun initView() {
        recyclerViewProducts.layoutManager = GridLayoutManager(requireContext(),3, RecyclerView.VERTICAL,false)
        productsAdapter = ProductsAdapter(requireContext(), listOf()) { product -> productClick(product) }
    }

    private fun initClick() {

    }

    private fun getProducts() {
        if (Utils.isNetworkAvailable(requireContext()))
            viewModel.getProducts()
        else
            Utils.showToast(requireContext(),getString(R.string.check_internet_connection))
    }

    private fun listenProductsData() {
        viewModel.post.observe(requireActivity(),{
            if (it!=null){
                if (it.isNotEmpty()){
                    productsAdapter.updateProducts(it)
                    recyclerViewProducts.adapter = productsAdapter
                }
            }
            else{

            }
        })
    }

    private fun productClick(product: ProductsData) {

    }

    override fun onDetach() {
        super.onDetach()
        viewModel.post.removeObservers(requireActivity())
    }

    override fun add(product: ProductsData, count: Int) {
        checkBasket(product.id)
        listenCheckBasketData(product,count)
    }

    override fun extract(product: ProductsData, count: Int) {
        checkBasket(product.id)
        listenCheckBasketData(product,count)
    }

    private fun checkBasket(productId: String) {
        viewModel.getBasketItem(requireContext(),productId)
    }

    private fun listenCheckBasketData(product: ProductsData, count: Int){
        viewModel.post.observe(requireActivity(), {
            if (it!=null) {
                viewModel.updateBasket(requireContext(),product.id,count)
            }
            else{
                viewModel.addToBasket(requireContext(),product.id,count)
            }

            viewModel.post.removeObservers(requireActivity())
        })
    }
}