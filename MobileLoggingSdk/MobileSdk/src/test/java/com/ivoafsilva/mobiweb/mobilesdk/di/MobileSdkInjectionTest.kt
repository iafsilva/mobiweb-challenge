package com.ivoafsilva.mobiweb.mobilesdk.di

import com.ivoafsilva.mobiweb.mobilesdk.MobileSdk
import com.ivoafsilva.mobiweb.mobilesdk.common.Logger
import com.ivoafsilva.mobiweb.mobilesdk.common.TestLogger
import com.ivoafsilva.mobiweb.mobilesdk.network.LoggingRepo
import com.ivoafsilva.mobiweb.mobilesdk.network.cloudfunctions.CloudFunctionsRepoImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class MobileSdkInjectionTest : KoinTest {

    private val testLogger = Logger().apply { set(TestLogger) }

    @Before
    fun setUp() {
        stopKoin()

        startKoin {
            modules(
                module { single { testLogger } },
                mobileSdkModule
            )
        }
    }

    @Test
    fun `should return true when DI is correct`(): Unit = runBlocking {
        val mobileSdk: MobileSdk by inject()
        val repo: LoggingRepo by inject()

        assertNotNull(mobileSdk)

        assertIs<CloudFunctionsRepoImpl>(repo)
        assertNotNull(repo)
    }

    // Seems a weird test but it actually tests the whole SDK implementation end to end.
    // From injection to the actual server call.
    // Should be commented out when this library ends up on CI pipelines.
    @Test
    fun `logMessage should return true when CloudFunctions service is up`(): Unit = runBlocking {
        val mobileSdk: MobileSdk by inject()
        assertTrue(mobileSdk.logMessage("Test Message"))
    }
}