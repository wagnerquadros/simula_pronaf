package com.wagner.simulapronaf.ui.components.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true)
@Composable
fun TituloCardPreview() {
    Column {
        TituloCard(texto = "Título à Esquerda")
        TituloCard(texto = "Título Centralizado", textAlign = TextAlign.Center)
        TituloCard(texto = "Título à Direita", textAlign = TextAlign.End)
    }
}