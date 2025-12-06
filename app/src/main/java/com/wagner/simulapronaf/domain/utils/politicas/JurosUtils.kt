package com.wagner.simulapronaf.domain.utils.politicas

import kotlin.math.pow

class JurosUtils : PoliticaJuros {

    override fun taxaEfetivaAnual(taxaNominalAA: Double, diasNoAno: Int): Double {
        val iNom = taxaNominalAA / 100.0
        return (1.0 + iNom / diasNoAno).pow(diasNoAno.toDouble()) - 1.0
    }

    override fun calcularJurosComTaxaEfetivaAnual(saldo: Double, taxaNominalAA: Double, diasNoAno: Int): Double {
        val taxaEfetiva = taxaEfetivaAnual(taxaNominalAA, diasNoAno)
        return saldo * (1.0 + taxaEfetiva)
    }
}