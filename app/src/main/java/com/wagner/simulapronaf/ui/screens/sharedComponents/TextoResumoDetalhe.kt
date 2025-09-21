package com.wagner.simulapronaf.ui.screens.sharedComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.theme.CinzaClaroTextoSecundario

@Composable
fun TextoResumoDetalhe(
    textoEsquerda: String,
    textoDireita: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textoEsquerda,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            color = CinzaClaroTextoSecundario
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = textoDireita,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = CinzaClaroTextoSecundario
        )
    }
}



@Preview(showBackground = true)
@Composable
fun TextoResumoDetalhePreview() {
    TextoResumoDetalhe("Exemplo", "10000")
}
