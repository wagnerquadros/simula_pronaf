package com.wagner.simulapronaf.domain.utils.politicas

interface PoliticaJuros {
    fun taxaEfetivaAnual(taxaNominalAA: Double, diasNoAno: Int): Double
    fun calcularJurosComTaxaEfetivaAnual(saldo: Double, taxaNominalAA: Double, diasNoAno: Int): Double
}