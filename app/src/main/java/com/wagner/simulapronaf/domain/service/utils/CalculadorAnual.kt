package com.wagner.simulapronaf.domain.service.utils

import com.wagner.simulapronaf.domain.models.Parcela
import com.wagner.simulapronaf.domain.models.SimulacaoEntrada
import com.wagner.simulapronaf.domain.models.SimulacaoResultado
import com.wagner.simulapronaf.domain.service.utils.politicas.AjusteResiduo
import com.wagner.simulapronaf.domain.service.utils.politicas.AjusteResiduoParcela
import com.wagner.simulapronaf.domain.service.utils.politicas.JurosUtils
import com.wagner.simulapronaf.domain.service.utils.politicas.PoliticaJuros
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

class CalculadorAnual(
    private val entrada: SimulacaoEntrada,
    private val dataBase: LocalDate = LocalDate.now(),
    
    private val ajusteResiduo: AjusteResiduo = AjusteResiduoParcela(),
    private val politicaJuros: PoliticaJuros = JurosUtils()
) {
    val DIAS_UTEIS_NO_ANO = 252

    fun calcular(): SimulacaoResultado {
        val listaParcelas = mutableListOf<Parcela>()
        val nAmortizacoes = entrada.prazo - entrada.carencia
        val amortizacaoAnualCapital = arredondar(entrada.valorSimulacao / (nAmortizacoes))
        var saldoCapital = entrada.valorSimulacao
        var jurosRemanescente = 0.0
        var saldoDevedor = arredondar(saldoCapital + jurosRemanescente)
        var anosRestantes = entrada.prazo
        var jurosCalculadosNaCarencia = 0.0

        for (ano in 1..entrada.carencia) {
            val saldoDuranteCarencia = politicaJuros.calcularJurosComTaxaEfetivaAnual(
                saldoDevedor, entrada.taxaDeJuros, DIAS_UTEIS_NO_ANO
            )
            saldoDevedor = arredondar(saldoDuranteCarencia)
            jurosCalculadosNaCarencia = arredondar(saldoDuranteCarencia - saldoCapital)
        }

        for (ano in 1 + entrada.carencia..entrada.prazo) {

            val dataVencimento = dataBase.plusYears(ano.toLong())
            val saldoInicialPeriodo = arredondar(
                saldoCapital + jurosRemanescente + jurosCalculadosNaCarencia
            )

            saldoDevedor = politicaJuros.calcularJurosComTaxaEfetivaAnual(
                saldoInicialPeriodo, entrada.taxaDeJuros, DIAS_UTEIS_NO_ANO
            )

            val jurosCalculadosNoAno = arredondar(saldoDevedor - saldoInicialPeriodo)

            anosRestantes = entrada.prazo - ano + 1

            val jurosPagos = arredondar((jurosRemanescente + jurosCalculadosNoAno) / anosRestantes)
            val valorParcela = arredondar(amortizacaoAnualCapital + jurosPagos)

            saldoCapital -= amortizacaoAnualCapital
            jurosRemanescente = arredondar((jurosRemanescente + jurosCalculadosNoAno) - jurosPagos)

            val saldoDevedorAtualizado = arredondar(saldoCapital + jurosRemanescente)

            listaParcelas.add(
                Parcela(
                    ano = ano,
                    valorParcela = valorParcela,
                    jurosPago = jurosPagos,
                    capitalPago = amortizacaoAnualCapital,
                    saldoDevedor = saldoDevedorAtualizado,
                    dataVencimento = dataVencimento
                )
            )
        }

        if (listaParcelas.isNotEmpty()) {
            val ultima = listaParcelas.last()
            if (ultima.saldoDevedor != 0.0) {
                listaParcelas[listaParcelas.lastIndex] = ajusteResiduo.ajustar(
                    ultima,
                    ::arredondar
                )
            }
        }

        return SimulacaoResultado(
            valorSimulacao = entrada.valorSimulacao,
            prazo = entrada.prazo,
            taxaDeJuros = entrada.taxaDeJuros,
            carencia = entrada.carencia,
            parcelas = listaParcelas
        )
    }
    private fun arredondar(valor: Double): Double {
        return BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }
}