package com.ivoafsilva.mobiweb.mobileapp.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivoafsilva.mobiweb.mobileapp.NavScreen
import com.ivoafsilva.mobiweb.mobileapp.R
import com.ivoafsilva.mobiweb.mobileapp.domain.UiState
import com.ivoafsilva.mobiweb.mobileapp.ui.components.MobileAppTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Scaffold(
        modifier = Modifier
            .testTag(NavScreen.HomeScreen.testTag)
            .fillMaxSize()
            .imePadding(),
        topBar = { MobileAppTopBar() },
        containerColor = MaterialTheme.colorScheme.onSecondary
    ) { innerPadding ->

        val text by viewModel.text.collectAsState()
        val uiState by viewModel.uiState.collectAsState()

        HomeContent(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            inputText = text,
            onTextChanged = viewModel::onTextChanged,
            onSaveClicked = viewModel::onSaveClicked,
        )
    }

}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: UiState,
    inputText: String,
    onTextChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedImage(uiState)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = onTextChanged,
            placeholder = { Text(text = stringResource(id = R.string.text_placeholder)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSaveClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(R.string.btn_save))
        }
    }
}

@Composable
fun AnimatedImage(uiState: UiState) {
    val infiniteTransition = rememberInfiniteTransition(label = "request_animation")
    val rotation by infiniteTransition.animateFloat(
        label = "rotation_animation",
        initialValue = 0f,
        targetValue = if (uiState is UiState.Loading) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val icon: ImageVector = when (uiState) {
        is UiState.Idle -> Icons.Filled.KeyboardArrowDown
        is UiState.Loading -> Icons.Filled.Refresh
        is UiState.Success -> Icons.Filled.Done
        is UiState.Error -> Icons.Filled.Warning
    }

    Image(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .graphicsLayer { rotationZ = rotation },
        colorFilter = ColorFilter.tint(Color.Black)
    )
}

@Preview(showBackground = true, name = "Idle State")
@Composable
fun PreviewIdleState() {
    HomeContent(
        uiState = UiState.Idle,
        inputText = "",
        onSaveClicked = {},
        onTextChanged = {}
    )
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun PreviewLoadingState() {
    HomeContent(
        uiState = UiState.Loading,
        inputText = "",
        onSaveClicked = {},
        onTextChanged = {}
    )
}

@Preview(showBackground = true, name = "Success State")
@Composable
fun PreviewSuccessState() {
    HomeContent(
        uiState = UiState.Success,
        inputText = "",
        onSaveClicked = {},
        onTextChanged = {}
    )
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun PreviewErrorState() {
    HomeContent(
        uiState = UiState.Error,
        inputText = "",
        onSaveClicked = {},
        onTextChanged = {}
    )
}