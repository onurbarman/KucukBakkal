package com.app.kucukbakkalapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.app.kucukbakkalapp.data.remote.Resource
import retrofit2.Response

object Utils {
    fun showToast(context : Context, message : String)
    {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                Resource.success(myResp.body()!!)
            } else {
                Resource.error(myResp.errorBody()?.string() ?: "Something goes wrong")
            }

        } catch (e: Exception) {
            Resource.error(e.message ?: "Internet error runs")
        }
    }
}