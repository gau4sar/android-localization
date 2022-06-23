package com.example.android_localization.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import com.example.android_localization.ui.theme.TransparentWhite


@Composable
fun fullPageProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TransparentWhite)
            .blur(radius = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SimpleCircularProgressIndicator()
    }
}

@Composable
fun SimpleCircularProgressIndicator() {
    CircularProgressIndicator()
}