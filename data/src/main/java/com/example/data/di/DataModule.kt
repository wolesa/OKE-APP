package com.example.data.di

import com.example.data.NetworkConnectionInterceptor
import com.example.data.repository.RemoteService
import com.example.data.repository.RepoImpl
import com.example.domain.repository.Repo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    val OK_HTTP = "OK_HTTP"
    val RETROFIT = "RETROFIT"

    val BASE_URL = "https://api.tvmaze.com/"

    single<Repo> { RepoImpl(get()) }

    single { NetworkConnectionInterceptor( get()) }

    single<OkHttpClient>(named(OK_HTTP)) {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
            .addInterceptor(NetworkConnectionInterceptor(get()))
            .build()
    }

    single<Retrofit>(named(RETROFIT)) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(get(named(OK_HTTP)))
            .build()
    }

    single<RemoteService> {
        get<Retrofit>(named(RETROFIT)).create(
            RemoteService::class.java)
    }

}