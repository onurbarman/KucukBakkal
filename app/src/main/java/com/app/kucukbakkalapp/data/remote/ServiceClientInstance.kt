package com.app.kucukbakkalapp.data.remote

import com.app.kucukbakkalapp.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceClientInstance {

    companion object {
        private var instance : ServiceClientInstance? = null

        fun getInstance():ServiceClientInstance {
            if (instance == null)
            {
                instance = ServiceClientInstance()
            }
            return instance!!
        }
    }

    val api: ServiceApi
        get() {
            return retrofit.create(ServiceApi::class.java)
        }

    private val retrofit:Retrofit


    init{
        val gson = GsonBuilder()
            .setLenient()
            .create()

            retrofit = Retrofit.Builder()
            .baseUrl(Constants.SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }



}