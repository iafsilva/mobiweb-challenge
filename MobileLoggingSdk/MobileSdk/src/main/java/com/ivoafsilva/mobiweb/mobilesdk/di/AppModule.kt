package com.ivoafsilva.mobiweb.mobilesdk.di

import com.ivoafsilva.mobiweb.mobilesdk.MobileSdk
import com.ivoafsilva.mobiweb.mobilesdk.common.Logger
import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import com.ivoafsilva.mobiweb.mobilesdk.network.api.CloudFunctionApi
import com.ivoafsilva.mobiweb.mobilesdk.network.cloudfunctions.CloudFunctionsRepoImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Koin module providing the necessary dependencies for the Mobile SDK.
 * It also exposes the [MobileSdk] itself.
 *
 * **Usage**:
 * This module should be loaded into Koin's application context as follows.
 * ```kotlin
 *    startKoin {
 *        androidContext(this@MyApplication)
 *        modules(mobileSdkModule)
 *    }
 * ```
 */
public val mobileSdkModule: Module = module {

    /**
     * The `Logger` instance is used throughout the SDK to handle internal logging operations.
     */
    single { Logger() }

    /**
     * Configures Retrofit to create an implementation of the [CloudFunctionApi] interface for making network requests.
     */
    single {
        Retrofit.Builder()
            .baseUrl(CloudFunctionApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CloudFunctionApi::class.java)
    }

    /**
     * This implementation uses the provided [CloudFunctionApi] and [Logger] to interact with the cloud functions API.
     */
    single<LoggingRepo> { CloudFunctionsRepoImpl(apiService = get(), logger = get()) }

    /**
     * Exposed our [MobileSdk] entry-point to the world
     */
    single { MobileSdk() }
}