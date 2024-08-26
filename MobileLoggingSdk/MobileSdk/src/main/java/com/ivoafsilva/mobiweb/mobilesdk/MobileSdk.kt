package com.ivoafsilva.mobiweb.mobilesdk

import com.ivoafsilva.mobiweb.mobilesdk.common.LogContract
import com.ivoafsilva.mobiweb.mobilesdk.common.Logger
import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * The `MobileSdk` class serves as the entry point for interacting with this SDK.
 *
 * It facilitates logging operations by providing easy-to-use methods to store log messages.
 *
 * Get an instance of [MobileSdk] by doing
 * ```kotlin
 * val sdk by inject()
 * ```
 */
public class MobileSdk internal constructor(): KoinComponent {

    private companion object {
        private const val TAG = "MobileSdk"
    }

    private val logger: Logger by inject()

    private val loggingRepo: LoggingRepo by inject()

    /**
     * Enables logging for this SDK.
     *
     * After providing a [contract] this SDK will call the appropriate [contract] methods in order to log its' internal info.
     *
     * @throws IllegalArgumentException if the logger is not properly initialized after setting the contract.
     *
     * @sample
     * val logContract = LogContractImpl()
     * mobileSdk.enableLogging(logContract)
     */
    public fun enableLogging(contract: LogContract) {
        logger.set(contract)
        requireNotNull(logger.get())
        logger.get()?.i(TAG, "enableLogging", "Logging for $TAG is now enabled!")
    }

    /**
     * Logs a message to the repository asynchronously and returns whether the operation was successful.
     *
     * @param message The message to be logged.
     *
     * @return `true` if the message was logged successfully, `false` otherwise.
     *
     * @sample
     * val success = mobileSdk.logMessage("This is a log message")
     * if (success) {
     *     println("Message logged successfully")
     * } else {
     *     println("Failed to log message")
     * }
     */
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