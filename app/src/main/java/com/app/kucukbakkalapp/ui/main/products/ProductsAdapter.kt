package com.app.kucukbakkalapp.ui.main.products

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.kucukbakkalapp.R
import com.app.kucukbakkalapp.data.model.products.ProductsData
import com.app.kucukbakkalapp.utils.Constants
import com.app.kucukbakkalapp.utils.GlideUtils

class ProductsAdapter(val context: Context, private var products: List<ProductsData>, private val onProductClick: (product: ProductsData) -> Unit )
    : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private var mLastClickTime : Long  = System.currentTimeMillis()
    private val CLICK_TIME_INTERVAL :  Long  = 1000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.item_products, parent, false)
        return ProductsViewHolder(view)
    }
    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun updateProducts(products: List<ProductsData>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        this.products = listOf()
        notifyDataSetChanged()
    }

    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgProduct : ImageView = itemView.findViewById(R.id.imgProduct)
        private val textProductPrice : TextView = itemView.findViewById(R.id.textProductPrice)
        private val textProductTitle : TextView = itemView.findViewById(R.id.textProductTitle)
        private val textItemCount : TextView = itemView.findViewById(R.id.textItemCount)
        private val btnAdd : Button = itemView.findViewById(R.id.btnAdd)
        private val btnExtract : Button = itemView.findViewById(R.id.btnExtract)
        private val linearProduct : LinearLayout = itemView.findViewById(R.id.linearProduct)
        private val constraintCount : ConstraintLayout = itemView.findViewById(R.id.constraintCount)

        fun bind(product: ProductsData) {
            itemView.setOnClickListener {
                val now : Long  = System.currentTimeMillis()
                if (now - mLastClickTime < CLICK_TIME_INTERVAL)
                    return@setOnClickListener

                mLastClickTime = now
                onProductClick.invoke(product)
            }

            btnAdd.setOnClickListener {
                val count = textItemCount.text.toString().toInt()
                if (product.stock>count){
                    constraintCount.visibility=View.VISIBLE
                    textItemCount.text=(count+1).toString()
                }
            }

            btnExtract.setOnClickListener {
                val count = textItemCount.text.toString().toInt()
                if (count<2){
                    constraintCount.visibility=View.INVISIBLE
                }
                textItemCount.text=(count-1).toString()
            }

            GlideUtils.urlToImageView(context, product.imageUrl,imgProduct)
            textProductPrice.text=product.currency+product.price
            textProductTitle.text=product.name

        }
    }
}