package com.wagner.simulapronaf.ui.screens.Cronograma

import android.R.attr.data
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.components.shared.BarraSuperior
import com.wagner.simulapronaf.ui.components.shared.DivisorHorizontal
import com.wagner.simulapronaf.ui.components.shared.TextoResumoDetalhe
import com.wagner.simulapronaf.ui.components.shared.TituloCard
import com.wagner.simulapronaf.ui.screens.Cronograma.components.DataValorParcelaText
import com.wagner.simulapronaf.ui.screens.Cronograma.components.DetalharCardExpandable
import com.wagner.simulapronaf.ui.screens.Cronograma.components.ParcelaSimulacao
import com.wagner.simulapronaf.ui.theme.CinzaClaroTextoSecundario
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo

@Composable
fun CronogramaScreen(
    data: String,
    valorSimulacao: Float,
    taxa: Float,
    juros: String,
    parcelas: Int,
    capital: String,
    saldo: String
) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        BarraSuperior(
            onSairClick = {
                activity?.finish()
            })

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
                        modifier = Modifier.fillMaxWidth().padding(4.dp),
                        text = "Os valores das parcelas são aproximados. A simulação realizada tem o objetivo apenas de subsidiar a tomada de decisão.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = CinzaClaroTextoSecundario,
                        textAlign = TextAlign.Justify
                    )

                    DivisorHorizontal()
                    TituloCard("Resumo", textAlign = TextAlign.Center)
                    TextoResumoDetalhe("Valor", "R\$ ${"%,.2f".format(valorSimulacao)}")
                    TextoResumoDetalhe("Taxa de Juros", "${"%.1f".format(taxa)}% a.a.")
                    TextoResumoDetalhe("Número de Parcelas", "$parcelas")
                    DivisorHorizontal()


                    Column {
                        ParcelaSimulacao()
                    }

                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun CronogramaScreenPreview() {
    CronogramaScreen(
        data = "01/01/2026",
        valorSimulacao = 100000f,
        taxa = 6.0f,
        juros = "3.000,00",
        parcelas = 6,
        capital = "32.000,00",
        saldo = "215.000,00"
    )
}