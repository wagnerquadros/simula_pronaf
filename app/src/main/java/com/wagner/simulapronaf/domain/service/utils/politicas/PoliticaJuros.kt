package com.wagner.simulapronaf.domain.service.utils.politicas

interface PoliticaJuros {
    fun taxaEfetivaAnual(taxaNominalAA: Double, diasNoAno: Int): Double
    fun calcularJurosComTaxaEfetivaAnual(saldo: Double, taxaNominalAA: Double, diasNoAno: Int): Double
}