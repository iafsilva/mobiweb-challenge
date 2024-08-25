package com.ivoafsilva.mobiweb.mobilesdk.network.cloudfunctions

import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import com.ivoafsilva.mobiweb.mobilesdk.network.api.CloudFunctionApi
import com.ivoafsilva.mobiweb.mobilesdk.network.model.RequestBody

internal class CloudFunctionsRepoImpl(private val apiService: CloudFunctionApi) : LoggingRepo {

    override suspend fun saveLog(message: String): Result<Unit> {
        return try {
            val requestBody = RequestBody(myString = message)
            val response = apiService.saveString(requestBody)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to log message"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}