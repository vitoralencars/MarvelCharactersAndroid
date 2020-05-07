package com.vitor.myapplication.di

import com.vitor.myapplication.service.interceptor.ServiceInterceptor
import com.vitor.myapplication.service.route.CharactersRoute
import com.vitor.myapplication.util.constant.ServiceConstants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val serviceModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(ServiceInterceptor(get()))
            .connectTimeout(ServiceConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(ServiceConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(ServiceConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(CharactersRoute::class.java) as CharactersRoute }
}