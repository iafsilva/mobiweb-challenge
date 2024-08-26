package com.ivoafsilva.mobiweb.mobilesdk.network.cloudfunctions

import com.ivoafsilva.mobiweb.mobilesdk.common.Logger
import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import com.ivoafsilva.mobiweb.mobilesdk.network.api.CloudFunctionApi
import com.ivoafsilva.mobiweb.mobilesdk.network.model.RequestBody

internal class CloudFunctionsRepoImpl(
    private val apiService: CloudFunctionApi,
    private val logger: Logger
) : LoggingRepo {

    private companion object {
        private const val TAG = "CloudFunctionsRepoImpl"
    }

    override suspend fun saveLog(message: String): Result<Unit> {
        logger.get()?.v(TAG, "saveLog", "entry")

        try {
            logger.get()?.v(TAG, "saveLog", "calling api")
            val requestBody = RequestBody(myString = message)
            val response = apiService.saveString(requestBody)

            if (!response.isSuccessful) {
                logger.get()?.d(TAG, "saveLog", "api returned failure")
                return Result.failure(Exception("Failed to save log message"))
            }

            logger.get()?.d(TAG, "saveLog", "api returned success")
            return Result.success(Unit)

        } catch (e: Exception) {
            logger.get()?.e(TAG, "saveLog", "api threw $e")
            return Result.failure(e)
        }
    }
}