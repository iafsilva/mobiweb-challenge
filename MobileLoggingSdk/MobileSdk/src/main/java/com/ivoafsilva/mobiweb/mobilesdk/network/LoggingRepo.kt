package com.ivoafsilva.mobiweb.mobilesdk.network

internal interface LoggingRepo {

    suspend fun saveLog(message: String): Result<Unit>

}