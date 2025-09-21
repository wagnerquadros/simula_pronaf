package com.wagner.simulapronaf.domain.models

import java.time.LocalDate

data class Parcela(
    val ano: Int,
    val valorParcela: Double,
    val jurosPago: Double,
    val capitalPago: Double,
    val saldoDevedor: Double,
    val dataVencimento: LocalDate
) {
    override fun toString(): String {
        return "Ano: $ano\n" +
                "valor da Parcela=$valorParcela\n" +
                "Juros Pago: $jurosPago\n" +
                "Capital Pago: $capitalPago\n" +
                "Saldo Devedor: $saldoDevedor\n" +
                "Data do Vencimento: $dataVencimento\n"
    }
}