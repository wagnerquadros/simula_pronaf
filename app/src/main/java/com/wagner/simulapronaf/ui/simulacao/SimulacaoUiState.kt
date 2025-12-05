package com.wagner.simulapronaf.ui.simulacao

data class SimulacaoUiState(
    val valorSimulacao: Float = 0f,
    val parcelas: Int = 1,
    val taxa: Float = 1.0f,
    val modalidade: String = "",
    val erroValor: String? = null
)