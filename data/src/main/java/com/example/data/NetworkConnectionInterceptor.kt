package com.example.data

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(isConnected()){
            return chain.proceed(chain.request())
        } else {
            throw IOException("You don't have internet connection")
        }
    }

    private fun isConnected(): Boolean =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo
            ?.isConnected
            ?: false
}