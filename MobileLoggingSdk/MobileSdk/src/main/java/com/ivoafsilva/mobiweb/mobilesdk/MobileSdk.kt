package com.ivoafsilva.mobiweb.mobilesdk

import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
class MobileSdk : KoinComponent {

    private val loggingRepo: LoggingRepo by inject()

    suspend fun logMessage(message: String): Boolean {
        return try {
            loggingRepo.saveLog(message)
            true
        } catch (e: Exception) {
            false
        }
    }
}