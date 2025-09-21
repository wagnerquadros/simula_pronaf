package com.wagner.simulapronaf.domain.models

data class SimulacaoResultado(
    val valorSimulacao: Double,
    val prazo: Int,
    val taxaDeJuros: Double,
    val carencia: Int,
    val parcelas: List<Parcela>
){
    override fun toString(): String {
        return "Valor da Simulacao: $valorSimulacao\n" +
                "Prazo: $prazo\n" +
                "Taxa De Juros: $taxaDeJuros\n" +
                "Carencia: $carencia\n" +
                "Parcelas ======================= \n $parcelas"
    }
}
