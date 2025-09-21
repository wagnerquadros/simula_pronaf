package com.wagner.simulapronaf.domain.service.impl

import com.wagner.simulapronaf.domain.models.SimulacaoEntrada
import com.wagner.simulapronaf.domain.models.SimulacaoResultado
import com.wagner.simulapronaf.domain.service.utils.CalculadorAnual

class SimulacaoService  {

    fun criarSimulacao(entrada: SimulacaoEntrada): SimulacaoResultado {
        return calcularParcelasAnuais(entrada)
    }

    private fun calcularParcelasAnuais(entrada: SimulacaoEntrada): SimulacaoResultado {
        val calculador = CalculadorAnual(entrada)
        return calculador.calcular()
    }


}

fun main(){

    val service = SimulacaoService()

    val entradaAnual = SimulacaoEntrada(
        valorSimulacao = 100000.00,
        prazo = 7,
        taxaDeJuros = 8.0,
        carencia = 0
    )

    val resultadoAnual = service.criarSimulacao(entradaAnual)

    println(resultadoAnual)
}

