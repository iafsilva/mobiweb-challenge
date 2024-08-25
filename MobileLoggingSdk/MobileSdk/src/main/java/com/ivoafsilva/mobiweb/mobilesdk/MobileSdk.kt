package com.ivoafsilva.mobiweb.mobilesdk

import androidx.annotation.Keep
import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
@Keep
public class MobileSdk : KoinComponent {

    private val loggingRepo: LoggingRepo by inject()

    public suspend fun logMessage(message: String): Boolean {
        return try {
            loggingRepo.saveLog(message)
            true
        } catch (e: Exception) {
            false
        }
    }
}