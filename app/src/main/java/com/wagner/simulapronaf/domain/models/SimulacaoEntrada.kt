package com.wagner.simulapronaf.domain.models

data class SimulacaoEntrada(
    val valorSimulacao: Double,
    val prazo: Int,
    val taxaDeJuros: Double,
    val carencia: Int,
    val modalidadeParcela: ModalidadeEnum = ModalidadeEnum.ANUAL
)

fun carenciaFromString(selecionado: String): Int = when (selecionado.trim()) {
    "Sem carência" -> 0
    "01 ano"       -> 1
    "02 anos"      -> 2
    "03 anos"      -> 3
    else           -> 0  // default
}