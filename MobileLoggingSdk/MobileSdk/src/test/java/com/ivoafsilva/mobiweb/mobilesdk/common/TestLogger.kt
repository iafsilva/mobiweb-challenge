package com.ivoafsilva.mobiweb.mobilesdk.common

/**
 * Facade for System Console
 */
object TestLogger : LogContract {

    override fun v(tag: String, method: String, message: String) {
        printToConsole(tag, method, message)
    }

    override fun d(tag: String, method: String, message: String) {
        printToConsole(tag, method, message)
    }

    override fun w(tag: String, method: String, message: String) {
        printToConsole(tag, method, message)
    }

    override fun i(tag: String, method: String, message: String) {
        printToConsole(tag, method, message)
    }

    override fun e(tag: String, method: String, message: String) {
        printToConsole(tag, method, message)
    }

    private fun printToConsole(tag: String, method: String, message: String) {
        println("$tag | $method | $message")
    }
}