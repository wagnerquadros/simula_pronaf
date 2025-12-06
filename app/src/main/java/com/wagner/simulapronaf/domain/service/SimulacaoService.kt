package com.wagner.simulapronaf.domain.service

import com.wagner.simulapronaf.domain.models.SimulacaoEntrada
import com.wagner.simulapronaf.domain.models.SimulacaoResultado
import com.wagner.simulapronaf.domain.utils.CalculadorAnual

class SimulacaoService : ICalculadoraSimulacao  {

    override fun criarSimulacao(entrada: SimulacaoEntrada): SimulacaoResultado {
        return calcularParcelasAnuais(entrada)
    }

    private fun calcularParcelasAnuais(entrada: SimulacaoEntrada): SimulacaoResultado {
        val calculador = CalculadorAnual(entrada)
        return calculador.calcular()
    }
}
