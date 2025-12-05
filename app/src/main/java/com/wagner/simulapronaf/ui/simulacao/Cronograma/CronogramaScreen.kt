package com.wagner.simulapronaf.ui.simulacao.Cronograma

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.domain.models.Parcela
import com.wagner.simulapronaf.domain.models.SimulacaoResultado
import com.wagner.simulapronaf.domain.utils.toBRL
import com.wagner.simulapronaf.ui.simulacao.Cronograma.components.ParcelasLista
import com.wagner.simulapronaf.ui.components.BarraSuperior
import com.wagner.simulapronaf.ui.components.DivisorHorizontal
import com.wagner.simulapronaf.ui.components.TextoResumoDetalhe
import com.wagner.simulapronaf.ui.components.TituloCard
import com.wagner.simulapronaf.ui.theme.CinzaClaroTextoSecundario
import com.wagner.simulapronaf.ui.theme.CorDoCard
import java.time.LocalDate

@Composable
fun CronogramaScreen(resultado: SimulacaoResultado) {

    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val scroll = rememberScrollState()

    Scaffold(
        topBar = {
            BarraSuperior(onSairClick = { activity?.finish() })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scroll),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Card(
                modifier = Modifier.padding(10.dp),
                colors = CardDefaults.cardColors(containerColor = CorDoCard),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        TituloCard("Cronograma completo")
                        DivisorHorizontal()
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            text = "Os valores das parcelas são aproximados. A simulação realizada tem o objetivo apenas de subsidiar a tomada de decisão.",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = CinzaClaroTextoSecundario,
                            textAlign = TextAlign.Justify
                        )

                        DivisorHorizontal()
                        TituloCard("Resumo", textAlign = TextAlign.Center)

                        TextoResumoDetalhe("Valor", resultado.valorSimulacao.toBRL())
                        TextoResumoDetalhe(
                            "Taxa de Juros",
                            "${"%.2f".format(resultado.taxaDeJuros)}% a.a."
                        )
                        TextoResumoDetalhe("Número de Parcelas", "${resultado.parcelas.size}")
                        DivisorHorizontal()

                        ParcelasLista(resultado.parcelas)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CronogramaScreenPreview() {
    CronogramaScreen(resultado = fakeResultado())

}

private fun fakeResultado(): SimulacaoResultado {
    val parcelas = (1..5).map { ano ->
        Parcela(
            ano = ano,
            valorParcela = 12_500.00,
            jurosPago = 3_500.00,
            capitalPago = 9_000.00,
            saldoDevedor = (100_000.00 - 9_000.00 * ano).coerceAtLeast(0.0),
            dataVencimento = LocalDate.now().plusYears(ano.toLong())
        )
    }
    return SimulacaoResultado(
        valorSimulacao = 100_000.00,
        prazo = 5,
        taxaDeJuros = 6.0,
        carencia = 1,
        parcelas = parcelas
    )
}