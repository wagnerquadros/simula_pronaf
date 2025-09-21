package com.wagner.simulapronaf.ui.screens.Cronograma.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ParcelaSimulacao(){

    Column {
        DataValorParcelaText("10/10/205", 100000.00f)
        DetalharCardExpandable(
            capital = 10000.00,
            juros = 1000.00,
            saldo = 10000.00
        )
    }

}

@Composable
@Preview(showBackground = true)
fun ParcelaSimulacaoPreview(){

    Column {
        ParcelaSimulacao()
        ParcelaSimulacao()
        ParcelaSimulacao()
        ParcelaSimulacao()
        ParcelaSimulacao()
    }

}