package com.wagner.simulapronaf.ui.screens.Cronograma.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wagner.simulapronaf.domain.models.Parcela
import com.wagner.simulapronaf.domain.service.utils.br

@Composable
fun ParcelasLista(parcelas: List<Parcela>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 0.dp, max = 520.dp)
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        items(parcelas) { p ->
            ParcelaItem(parcela = p)
        }
    }
}

@Composable
fun ParcelaItem(parcela: Parcela) {
    Column(modifier = Modifier.fillMaxWidth()) {
        DataValorParcelaText(
            data = parcela.dataVencimento.br(),
            valorParcela = parcela.valorParcela.toFloat()
        )
        DetalharCardExpandable(
            capital = parcela.capitalPago,
            juros = parcela.jurosPago,
            saldo = parcela.saldoDevedor
        )
    }
}