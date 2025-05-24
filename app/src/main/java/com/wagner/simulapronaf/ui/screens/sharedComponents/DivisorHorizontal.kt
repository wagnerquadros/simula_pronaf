package com.wagner.simulapronaf.ui.screens.sharedComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wagner.simulapronaf.ui.theme.CinzaTextoPrimario

@Composable
fun DivisorHorizontal(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 10.dp),
        thickness = 1.2.dp,
        color = CinzaTextoPrimario
    )
}