package com.app.kucukbakkalapp.data.repository.products

import com.app.kucukbakkalapp.data.model.order.OrderModel
import com.app.kucukbakkalapp.data.model.order_complete.OrderCompleteModel
import com.app.kucukbakkalapp.data.model.products.ProductsModel
import com.app.kucukbakkalapp.data.remote.Resource
import com.app.kucukbakkalapp.data.remote.ServiceClientInstance
import com.app.kucukbakkalapp.utils.Utils.safeApiCall

class ProductsRepository {
    suspend fun getProducts(): Resource<ProductsModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.getProducts() })
    }

    suspend fun postOrder(orders: OrderModel): Resource<OrderCompleteModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.postOrderComplete(orders) })
    }
}