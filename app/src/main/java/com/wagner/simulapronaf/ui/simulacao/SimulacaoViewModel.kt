package com.wagner.simulapronaf.ui.simulacao

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wagner.simulapronaf.domain.models.ModalidadeEnum
import com.wagner.simulapronaf.domain.models.SimulacaoEntrada
import com.wagner.simulapronaf.domain.models.SimulacaoResultado
import com.wagner.simulapronaf.domain.models.carenciaFromString
import com.wagner.simulapronaf.domain.service.ICalculadoraSimulacao
import com.wagner.simulapronaf.domain.service.SimulacaoService
import com.wagner.simulapronaf.ui.simulacao.SimulacaoRapida.components.formatarValor

class SimulacaoViewModel (
    private val calculadoraSimulacao: ICalculadoraSimulacao = SimulacaoService()
) : ViewModel() {

    var estado by mutableStateOf(SimulacaoUiState())
        private set

    var ultimoResultado by mutableStateOf<SimulacaoResultado?>(null)
        private set

    fun simular(entrada: SimulacaoEntrada): SimulacaoResultado {
        val resultado = calculadoraSimulacao.criarSimulacao(entrada)
        ultimoResultado = resultado
        return resultado
    }

    fun aoMudarValorSimulacao(novoValor: Float) {
        estado = estado.copy(valorSimulacao = novoValor)
    }

    fun aoMudarParcelas(novoValor: Int) {
        estado = estado.copy(parcelas = novoValor)
    }

    fun aoMudarTaxa(novoValor: Float) {
        estado = estado.copy(taxa = novoValor)
    }

    fun aoMudarModalidade(novaModalidade: String) {
        estado = estado.copy(modalidade = novaModalidade)
    }

    fun definirErroValor(mensagem: String?) {
        estado = estado.copy(erroValor = mensagem)
    }

    fun limparErroValor() {
        estado = estado.copy(erroValor = null)
    }

    fun tentarSimular(minimo: Int, maximo: Int): Boolean {
        val valorInt = estado.valorSimulacao.toInt()

        if (valorInt !in minimo..maximo) {
            val mensagem = "Informe um valor entre ${formatarValor(minimo)} e ${formatarValor(maximo)}"
            definirErroValor(mensagem)
            return false
        }

        limparErroValor()

        val entrada = SimulacaoEntrada(
            valorSimulacao = estado.valorSimulacao.toDouble(),
            prazo = estado.parcelas,
            taxaDeJuros = estado.taxa.toDouble(),
            carencia = carenciaFromString(estado.modalidade),
            modalidadeParcela = ModalidadeEnum.ANUAL
        )

        simular(entrada)
        return true
    }
}