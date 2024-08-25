
package com.ivoafsilva.mobiweb.mobileapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides all [CoroutineDispatcher]s
 * Useful abstraction because Dispatchers may be different depending on environment. (E.g. unit tests)
 */
@Suppress("unused")
interface DispatcherProvider {
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

/**
 * Default Dispatchers redirecting to [Dispatchers]
 */
class DefaultDispatcherProvider : DispatcherProvider