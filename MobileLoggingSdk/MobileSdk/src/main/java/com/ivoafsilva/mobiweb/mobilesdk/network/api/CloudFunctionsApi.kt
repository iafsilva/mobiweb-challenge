package com.ivoafsilva.mobiweb.mobilesdk.network.api

import com.ivoafsilva.mobiweb.mobilesdk.network.model.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Retrofit API interface for interacting with the cloud functions.
 */
internal interface CloudFunctionApi {

    companion object {
        const val BASE_URL = "https://us-central1-mobilesdklogging.cloudfunctions.net/"
    }

    @Headers("Content-Type: application/json")
    @POST("saveString")
    suspend fun saveString(@Body body: RequestBody): retrofit2.Response<Void>
}