package com.app.kucukbakkalapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.kucukbakkalapp.R
import com.app.kucukbakkalapp.ui.main.products.ProductsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        openProductsPage()
    }

    private fun openProductsPage(){
        val fragmentCount: Int = supportFragmentManager.backStackEntryCount
        for (i in 0 until fragmentCount) {
            supportFragmentManager.popBackStackImmediate()
        }

        val productsFragment = ProductsFragment()
        replaceFragment(productsFragment, "ProductsFragment")

    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            if (fragment.isAdded) {
                show(fragment)
            } else {
                add(R.id.frameLayout, fragment, tag)
            }

            supportFragmentManager.fragments.forEach {
                if (it != fragment && it.isAdded) {
                    hide(it)
                }
            }
        }.commit()
    }


    fun loadFragment(fragment: Fragment?, tag: String): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout,fragment,tag)
                .addToBackStack(tag)
                .commit()
            return true
        }
        return false
    }
}