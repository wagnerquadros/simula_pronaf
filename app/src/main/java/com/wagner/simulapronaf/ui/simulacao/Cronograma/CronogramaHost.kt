package com.wagner.simulapronaf.ui.simulacao.Cronograma

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wagner.simulapronaf.ui.simulacao.SimulacaoViewModel

@Composable
fun CronogramaHost(simulacaoViewModel: SimulacaoViewModel) {
    val resultado = simulacaoViewModel.ultimoResultado
    if (resultado != null) {
        CronogramaScreen(resultado)
    } else {
        Text("Nenhuma simulação encontrada. Volte e realize uma simulação.")
    }
}