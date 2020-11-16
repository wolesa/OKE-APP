package com.example.oketestapp

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.interactionModule
import com.example.oketestapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin(){
        startKoin{
            androidContext(this@BaseApp)

            modules(
                listOf(
                    viewModelModule,
                    interactionModule,
                    dataModule
                )
            )
        }
    }

}