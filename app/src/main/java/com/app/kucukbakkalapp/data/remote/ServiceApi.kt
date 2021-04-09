package com.app.kucukbakkalapp.data.remote

import com.app.kucukbakkalapp.data.model.order.OrderModel
import com.app.kucukbakkalapp.data.model.order_complete.OrderCompleteModel
import com.app.kucukbakkalapp.data.model.products.ProductsModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApi {
    //Get Products
    @GET("list")
    suspend fun getProducts(): Response<ProductsModel>

    //Post Order
    @POST("checkout")
    suspend fun postOrderComplete(@Body body: OrderModel): Response<OrderCompleteModel>
}