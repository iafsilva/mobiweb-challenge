package com.ivoafsilva.mobiweb.mobilesdk.common

internal class Logger {

    private var log: LogContract? = null

    internal fun set(logger: LogContract?) {
        log = logger
    }

    internal fun get(): LogContract? {
        return log
    }
}