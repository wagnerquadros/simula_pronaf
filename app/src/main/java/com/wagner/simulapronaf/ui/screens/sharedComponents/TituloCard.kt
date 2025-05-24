package com.wagner.simulapronaf.ui.screens.sharedComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.theme.CinzaTextoPrimario

@Composable
fun TituloCard(
    texto: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = texto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = CinzaTextoPrimario,
        textAlign = textAlign,
        modifier = modifier.fillMaxWidth()
    )
}
