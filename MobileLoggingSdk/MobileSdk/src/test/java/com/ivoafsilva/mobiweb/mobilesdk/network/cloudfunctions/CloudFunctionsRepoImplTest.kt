package com.ivoafsilva.mobiweb.mobilesdk.network.cloudfunctions

import com.ivoafsilva.mobiweb.mobilesdk.common.Logger
import com.ivoafsilva.mobiweb.mobilesdk.network.api.CloudFunctionApi
import com.ivoafsilva.mobiweb.mobilesdk.network.model.RequestBody
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CloudFunctionsRepoImplTest {

    private lateinit var repo: CloudFunctionsRepoImpl
    private val api: CloudFunctionApi = mockk()
    private val logger: Logger = Logger()

    @Before
    fun setUp() {
        repo = CloudFunctionsRepoImpl(api, logger)
    }

    @Test
    fun `saveLog should call saveString on Api and return successfully`() = runBlocking {
        val message = "Test Message"
        val requestBody = RequestBody(message)

        // Mock the API service to return successfully
        coEvery { api.saveString(requestBody) } returns retrofit2.Response.success<Void>(200, null)

        val result = repo.saveLog(message)

        // Verify that saveString was called on the API service with the correct data
        coVerify { api.saveString(requestBody) }
        // Verify that return type is correct
        assertEquals(result, Result.success(Unit))
    }

    @Test
    fun `saveLog should throw an exception when the API call fails`() = runBlocking {
        val message = "Test Message"
        val requestBody = RequestBody(message)

        // Mock the API service to throw an exception
        coEvery { api.saveString(requestBody) } returns retrofit2.Response.error(404, ResponseBody.create(MediaType.parse("plain/text"), "Not Found"))

        val result = repo.saveLog(message)

        // Verify that saveString was still called on the API service
        coVerify { api.saveString(requestBody) }

        // Verify exception message is the expected same
        assertNotNull(result.exceptionOrNull())
        assertEquals(result.exceptionOrNull()!!.message, "Failed to log message")
    }

    @Test
    fun `saveLog should throw an exception when the API call throws`() = runBlocking {
        val message = "Test Message"
        val requestBody = RequestBody(message)

        // Mock the API service to throw an exception
        coEvery { api.saveString(requestBody) } throws RuntimeException("API failure")

        // Call the logMessage method
        val result = repo.saveLog(message)

        // Verify that saveString was still called on the API service
        coVerify { api.saveString(requestBody) }

        // Verify exception message is the expected same
        assertNotNull(result.exceptionOrNull())
        assertEquals(result.exceptionOrNull()!!.message, "API failure")
    }
}