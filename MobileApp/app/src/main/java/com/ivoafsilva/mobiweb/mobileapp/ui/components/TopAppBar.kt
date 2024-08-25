package com.ivoafsilva.mobiweb.mobileapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ivoafsilva.mobiweb.mobileapp.R

/**
 * The AppBar Component
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MobileAppTopBar(
    title: @Composable () -> Unit = { TopAppBarTitle() },
) {
    TopAppBar(
        title = title,
        modifier = Modifier
            .statusBarsPadding(),
    )
}

/**
 * The Default AppBar Title
 */
@Composable
private fun TopAppBarTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.app_top_bar_title),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}