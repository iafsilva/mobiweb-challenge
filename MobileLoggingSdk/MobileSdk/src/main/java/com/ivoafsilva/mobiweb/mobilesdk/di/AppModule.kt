package com.ivoafsilva.mobiweb.mobilesdk.di

import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import com.ivoafsilva.mobiweb.mobilesdk.network.api.CloudFunctionApi
import com.ivoafsilva.mobiweb.mobilesdk.network.cloudfunctions.CloudFunctionsRepoImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://us-central1-mobilesdklogging.cloudfunctions.net/"


val mobileSdkModule: Module = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CloudFunctionApi::class.java)
    }

    single<LoggingRepo> { CloudFunctionsRepoImpl(get()) }
}