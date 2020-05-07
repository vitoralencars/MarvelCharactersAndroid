package com.vitor.myapplication.service.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.vitor.myapplication.service.error.CallbackHelper
import com.vitor.myapplication.util.ProgressBarHandler
import com.vitor.myapplication.util.ServiceHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ServiceInterceptor(private val context: Context) : Interceptor {

    private val errorHandler = object : CallbackHelper(){}

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        requestBuilder.method(request.method(), request.body())

        clearServiceErrors()

        try {
            val requestCopy = requestBuilder.build()
            val response = chain.proceed(requestCopy)

            val responseCode = response.code()

            if(responseCode != 200) {
                when (response.code()) {
                    401 -> errorHandler.onRequestError()
                    500 -> errorHandler.onServerError()
                    else -> errorHandler.onServerError()
                }
            }

            return response

        }catch (e: SocketTimeoutException){
            errorHandler.onTimeOutError()
        }catch (e: IOException){
            if(!isNetworkAvailable()){
                errorHandler.onNotConnectedError()
            }else{
                errorHandler.onRequestError()
            }
        }finally {
            ProgressBarHandler.hideProgressBar()
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun clearServiceErrors(){
        ServiceHelper.notConnected.postValue(false)
        ServiceHelper.generalError.postValue(false)
    }

    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork
                ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
                ?: return false

            return when{
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    else -> false
                }
            }
            false
        }
    }

}