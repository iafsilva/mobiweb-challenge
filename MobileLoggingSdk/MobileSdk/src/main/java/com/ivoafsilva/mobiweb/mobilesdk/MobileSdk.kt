package com.ivoafsilva.mobiweb.mobilesdk

import com.ivoafsilva.mobiweb.mobilesdk.common.LogContract
import com.ivoafsilva.mobiweb.mobilesdk.common.Logger
import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
public class MobileSdk : KoinComponent {

    private companion object {
        private const val TAG = "MobileSdk"
    }

    private val loggingRepo: LoggingRepo by inject()

    private val logger: Logger by inject()

    public fun enableLogging(contract: LogContract) {
        logger.set(contract)
        requireNotNull(logger.get())
        logger.get()?.i(TAG, "enableLogging", "Logging for $TAG is now enabled!")
    }

    public suspend fun logMessage(message: String): Boolean {
        logger.get()?.v(TAG, "logMessage", "entry")

        return try {
            logger.get()?.v(TAG, "logMessage", "calling repo")

            loggingRepo.saveLog(message)

            logger.get()?.d(TAG, "logMessage", "returning success")
            true

        } catch (e: Exception) {
            logger.get()?.d(TAG, "logMessage", "returning failure")
            false
        }
    }
}