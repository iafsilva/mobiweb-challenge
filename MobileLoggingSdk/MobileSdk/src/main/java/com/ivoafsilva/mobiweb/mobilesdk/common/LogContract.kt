package com.ivoafsilva.mobiweb.mobilesdk.common

public interface LogContract {

    public fun v(tag: String, method: String, message: String)

    public fun d(tag: String, method: String, message: String)

    public fun i(tag: String, method: String, message: String)

    public fun w(tag: String, method: String, message: String)

    public fun e(tag: String, method: String, message: String)

}