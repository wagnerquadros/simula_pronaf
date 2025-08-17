package com.wagner.simulapronaf.ui.components.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true)
@Composable
fun DivisorHorizontalPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        DivisorHorizontal()
    }
}