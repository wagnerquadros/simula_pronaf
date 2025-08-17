package com.wagner.simulapronaf.domain.models

data class SimulacaoEntrada(
    val valorSimulacao: Double,
    val prazo: Int,
    val taxaDeJuros: Double,
    val carencia: Int,
    val modalidadeParcela: ModalidadeEnum = ModalidadeEnum.ANUAL
)
