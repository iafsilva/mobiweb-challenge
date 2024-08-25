package com.ivoafsilva.mobiweb.mobilesdk

import com.ivoafsilva.mobiweb.mobilesdk.common.Logger
import com.ivoafsilva.mobiweb.mobilesdk.common.TestLogger
import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class MobileSdkTest : KoinTest {

    private val loggingRepo: LoggingRepo = mockk()
    private val mobileSdk: MobileSdk by inject()
    private val testLogger = Logger().apply { set(TestLogger) }

    @Before
    fun setUp() {
        stopKoin()

        startKoin {
            modules(
                module {
                    single { testLogger }
                    single { loggingRepo }
                    single { MobileSdk() }
                }
            )
        }
    }

    @Test
    fun `logMessage should return true when loggingRepo succeeds`(): Unit = runBlocking {
        val message = "Test Message"

        // Mock the loggingRepo to succeed
        coEvery { loggingRepo.saveLog(message) } returns Result.success(Unit)

        val result = mobileSdk.logMessage(message)

        assertTrue(result)
        coVerify { loggingRepo.saveLog(message) }
    }

    @Test
    fun `logMessage should return false when loggingRepo throws an exception`() = runBlocking {
        val message = "Test Message"

        // Mock the loggingRepo to throw an exception
        coEvery { loggingRepo.saveLog(message) } throws RuntimeException("Logging failed")

        val result = mobileSdk.logMessage(message)

        assertFalse(result)
        coVerify { loggingRepo.saveLog(message) }
    }
}