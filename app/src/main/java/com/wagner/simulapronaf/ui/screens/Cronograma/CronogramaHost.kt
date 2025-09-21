package com.wagner.simulapronaf.ui.screens.Cronograma

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wagner.simulapronaf.ui.navigation.SimulacaoViewModel

@Composable
fun CronogramaHost(vmCompartilhado: SimulacaoViewModel) {
    val resultado = vmCompartilhado.ultimoResultado
    if (resultado != null) {
        CronogramaScreen(resultado)
    } else {
        Text("Nenhuma simulação encontrada. Volte e realize uma simulação.")
    }
}