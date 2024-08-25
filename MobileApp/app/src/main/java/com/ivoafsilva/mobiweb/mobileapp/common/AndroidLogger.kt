package com.ivoafsilva.mobiweb.mobileapp.common

import android.util.Log
import com.ivoafsilva.mobiweb.mobilesdk.common.LogContract

/**
 * Facade for Android Logger
 */
object AndroidLogger : LogContract {

    override fun v(tag: String, method: String, message: String) {
        Log.v(tag, "$method: $message")
    }

    override fun d(tag: String, method: String, message: String) {
        Log.d(tag, "$method: $message")
    }

    override fun w(tag: String, method: String, message: String) {
        Log.w(tag, "$method: $message")
    }

    override fun i(tag: String, method: String, message: String) {
        Log.i(tag, "$method: $message")
    }

    override fun e(tag: String, method: String, message: String) {
        Log.e(tag, "$method: $message")
    }
}