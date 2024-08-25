package com.ivoafsilva.mobiweb.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ivoafsilva.mobiweb.mobileapp.common.AndroidLogger
import com.ivoafsilva.mobiweb.mobileapp.ui.theme.MobileAppTheme
import com.ivoafsilva.mobiweb.mobilesdk.MobileSdk
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val mobileSdk: MobileSdk by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start logging Mobile SDK to Logcat
        mobileSdk.enableLogging(AndroidLogger)

        enableEdgeToEdge()
        setContent {
            MobileAppTheme {
                SetupNavGraph(startDestination = NavScreen.HomeScreen.route)
            }
        }
    }
}