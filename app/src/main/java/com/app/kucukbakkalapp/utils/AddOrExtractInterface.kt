package com.app.kucukbakkalapp.utils

import com.app.kucukbakkalapp.data.model.products.ProductsData

interface AddOrExtractInterface {
    fun add(product: ProductsData, count: Int)
    fun extract(product: ProductsData, count: Int)
}