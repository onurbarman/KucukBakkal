package com.app.kucukbakkalapp.data.model.products

data class ProductsData(
    val currency: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Double,
    val stock: Int
)