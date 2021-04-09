package com.app.kucukbakkalapp.ui.main.products

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.kucukbakkalapp.data.model.products.ProductsModel
import com.app.kucukbakkalapp.data.repository.products.ProductsRepository
import com.app.kucukbakkalapp.data.room.Basket
import com.app.kucukbakkalapp.data.room.BasketDatabase
import com.app.kucukbakkalapp.ui.custom.SingleLiveEvent
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private val productRepository : ProductsRepository = ProductsRepository()

    val post: MutableLiveData<ProductsModel> by lazy {
        MutableLiveData<ProductsModel>()
    }

    fun getProducts() {
        viewModelScope.launch {
            val retrofitPost = productRepository.getProducts()
            if (retrofitPost.data!=null)
                post.postValue(retrofitPost.data)
        }

    }

    val postBasket: SingleLiveEvent<Basket?> by lazy {
        SingleLiveEvent<Basket?>()
    }

    fun getBasketItem(context: Context, id : String) {
        viewModelScope.launch {
            val basket : Basket? =  BasketDatabase(context).getBasketDao().getBasketItem(id)
            postBasket.postValue(basket)

        }

    }

    val postBasketResponse: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun addToBasket(context : Context,id : String,count: Int) {
        viewModelScope.launch {
            val basket = Basket(id, count)
            BasketDatabase(context).getBasketDao().addBasket(basket)
            postBasketResponse.postValue(true)
            Log.d("Basket","Basket eklendi")
        }

    }

    fun updateBasket(context : Context,id : String,count: Int) {
        viewModelScope.launch {
            val basket = Basket(id, count)
            BasketDatabase(context).getBasketDao().updateBasket(basket)
            postBasketResponse.postValue(true)
            Log.d("Basket","Basket guncellendi")
        }

    }

}