package com.ivoafsilva.mobiweb.mobilesdk.network

interface LoggingRepo {

    suspend fun saveLog(message: String): Result<Unit>

}