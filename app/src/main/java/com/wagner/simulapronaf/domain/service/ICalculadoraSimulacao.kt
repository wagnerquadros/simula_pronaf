package com.wagner.simulapronaf.domain.service

import com.wagner.simulapronaf.domain.models.SimulacaoEntrada
import com.wagner.simulapronaf.domain.models.SimulacaoResultado

interface ICalculadoraSimulacao {
    fun criarSimulacao(entrada: SimulacaoEntrada): SimulacaoResultado
}