package com.ivoafsilva.mobiweb.mobilesdk.common

/**
 * Interface for logging operations within the SDK.
 *
 * This interface defines methods for logging messages at different levels: verbose, debug, informational, warning, and error.
 * Implementations of this interface are used to handle logging in various parts of the SDK.
 *
 * **Logging Levels**:
 * - `v`: Verbose logging, typically used for detailed debugging information.
 * - `d`: Debug logging, used for debugging information.
 * - `i`: Informational logging, used for general information and progress updates.
 * - `w`: Warning logging, used for potential issues or important warnings.
 * - `e`: Error logging, used for error messages and exceptions.
 */
public interface LogContract {

    /**
     * Logs a verbose message.
     *
     * @param tag A tag used to identify the source of the log message.
     * @param method The method or function where the logging occurs.
     * @param message The message to be logged.
     */
    public fun v(tag: String, method: String, message: String)

    /**
     * Logs a debug message.
     *
     * @param tag A tag used to identify the source of the log message.
     * @param method The method or function where the logging occurs.
     * @param message The message to be logged.
     */
    public fun d(tag: String, method: String, message: String)

    /**
     * Logs an informational message.
     *
     * @param tag A tag used to identify the source of the log message.
     * @param method The method or function where the logging occurs.
     * @param message The message to be logged.
     */
    public fun i(tag: String, method: String, message: String)

    /**
     * Logs a warning message.
     *
     * @param tag A tag used to identify the source of the log message.
     * @param method The method or function where the logging occurs.
     * @param message The message to be logged.
     */
    public fun w(tag: String, method: String, message: String)

    /**
     * Logs an error message.
     *
     * @param tag A tag used to identify the source of the log message.
     * @param method The method or function where the logging occurs.
     * @param message The message to be logged.
     */
    public fun e(tag: String, method: String, message: String)
}