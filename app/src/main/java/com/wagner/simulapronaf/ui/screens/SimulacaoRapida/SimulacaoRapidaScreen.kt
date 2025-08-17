package com.wagner.simulapronaf.ui.screens.SimulacaoRapida

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagner.simulapronaf.ui.components.shared.BarraSuperior
import com.wagner.simulapronaf.ui.components.shared.BotaoPrimario
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.ParcelasCard
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.TaxaCard
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.ValorCard

@Composable
fun SimulacaoRapidaScreen() {
    var valorSimulacao by remember { mutableStateOf(1000f) }
    var parcelas by remember { mutableStateOf(1) }
    var carencia by remember { mutableStateOf(1) }
    var taxa by remember { mutableStateOf(1.0f) }
    var modalidade by remember { mutableStateOf("Anual") }
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
            }
        )

        ValorCard(
            valorSimulacao = valorSimulacao,
            onValorChange = { valorSimulacao = it }
        )

        ParcelasCard(
            parcelas = parcelas,
            carencia = carencia,
            onParcelasChange = { parcelas = it },
            modalidade = modalidade,
            onModalidadeChange = { modalidade = it }
        )

        TaxaCard(
            taxa = taxa,
            onValorChange = { taxa = it }
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            BotaoPrimario(
                texto = "Simular",
                onClick = {
                    Toast.makeText(context, "Apertou o botão", Toast.LENGTH_SHORT).show()
                    println("VALOR: R$ $valorSimulacao")
                    println("MODALIDADE: $modalidade")
                    println("PRAZO: $parcelas anos")
                    println("TAXA: $taxa% a.a.")
                }
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun SimulacaoRapidaScreenPreview() {
    SimulacaoRapidaScreen()
}