package com.wagner.simulapronaf.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wagner.simulapronaf.domain.models.SimulacaoResultado

class SimulacaoViewModel : ViewModel() {
    var ultimoResultado by mutableStateOf<SimulacaoResultado?>(null)
        private set

    fun atualizarResultado(r: SimulacaoResultado) { ultimoResultado = r }
}