package com.ivoafsilva.mobiweb.mobileapp

import android.app.Application
import com.ivoafsilva.mobiweb.mobileapp.di.appModule
import com.ivoafsilva.mobiweb.mobilesdk.di.mobileSdkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(mobileSdkModule)
            modules(appModule)
        }
    }
}