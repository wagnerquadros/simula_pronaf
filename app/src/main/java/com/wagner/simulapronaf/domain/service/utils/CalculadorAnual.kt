package com.wagner.simulapronaf.domain.service.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.wagner.simulapronaf.domain.models.Parcela
import com.wagner.simulapronaf.domain.models.SimulacaoEntrada
import com.wagner.simulapronaf.domain.models.SimulacaoResultado
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import kotlin.math.pow

class CalculadorAnual (
    private val entrada: SimulacaoEntrada
){
    val DIAS_UTEIS_NO_ANO = 252

    fun calcular(): SimulacaoResultado {
        val listaParcelas = mutableListOf<Parcela>()
        val amortizacaoAnualCapital = arredondar(entrada.valorSimulacao / (entrada.prazo - entrada.carencia))
        var saldoCapital = entrada.valorSimulacao
        var jurosRemanescente = 0.0
        var saldoDevedor = arredondar(saldoCapital + jurosRemanescente)
        var anosRestantes = entrada.prazo
        var jurosCalculadosNaCarencia = 0.0

        println("Amortixação de capital anual: " + amortizacaoAnualCapital)

        for (ano in 1 .. entrada.carencia){
            var saldoDuranteCarencia = calcularJurosComTaxaEfetivaAnual(saldoDevedor, entrada.taxaDeJuros)
            jurosCalculadosNaCarencia = arredondar(saldoDuranteCarencia - saldoCapital)
            println("================================= Juros carencia : " + jurosCalculadosNaCarencia)
            println("================================= saldo da carenca : " + saldoDuranteCarencia)
        }

        for (ano in 1 + entrada.carencia .. entrada.prazo){
            val dataVencimento = LocalDate.now().plusYears(ano.toLong())
            val saldoInicialPeriodo = arredondar(saldoCapital + jurosRemanescente + jurosCalculadosNaCarencia)
            //val saldoInicial = saldoDevedor


            saldoDevedor = calcularJurosComTaxaEfetivaAnual(saldoInicialPeriodo, entrada.taxaDeJuros)

            val jurosCalculadosNoAno = arredondar(saldoDevedor - saldoInicialPeriodo)
            anosRestantes = entrada.prazo - ano + 1
            val jurosPagos = arredondar((jurosRemanescente + jurosCalculadosNoAno) / anosRestantes)
            val valorParcela = arredondar(amortizacaoAnualCapital + jurosPagos)

            saldoCapital -= amortizacaoAnualCapital
            jurosRemanescente = arredondar((jurosRemanescente + jurosCalculadosNoAno) - jurosPagos)

            val saldoDevedorAtualizado = arredondar(saldoCapital + jurosRemanescente)

            if(ano == entrada.prazo){
                println("Juros calculados: " + jurosCalculadosNoAno)
                println("Juros pagos: " + jurosPagos)
                println("Valor da Parcela: " + valorParcela)
                println("Juros remancente: " + jurosRemanescente)
                println("saldo capital: " + saldoCapital)
                println("===============================")
            }

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
        return SimulacaoResultado(
            valorSimulacao = entrada.valorSimulacao,
            prazo = entrada.prazo,
            taxaDeJuros = entrada.taxaDeJuros,
            carencia = entrada.carencia,
            parcelas = listaParcelas
        )
    }

    private fun calcularTaxaDiaria(taxaDeJuros: Double): Double{
        return (1 + (taxaDeJuros / 100)).pow(1.0 / DIAS_UTEIS_NO_ANO) - 1
    }

    fun arredondar(valor: Double): Double {
        return BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    fun calcularJuros(saldo: Double, taxaDeJuros: Double): Double {
        val taxaDiaria = calcularTaxaDiaria(taxaDeJuros)
        var saldoAtualizado = saldo
        repeat(DIAS_UTEIS_NO_ANO) {
            saldoAtualizado *= (1.0 + taxaDiaria)
        }
        return saldoAtualizado
    }

    fun calcularJurosComTaxaEfetivaAnual(saldo: Double, taxaNominalAA: Double): Double {
        val taxaEfetiva = taxaEfetivaAnual(taxaNominalAA) // ≈ 6,1829% para 6% nominal
        return saldo * (1.0 + taxaEfetiva)
    }

    fun taxaEfetivaAnual(taxaNominalAA: Double, diasNoAno: Int = DIAS_UTEIS_NO_ANO): Double {
        val iNom = taxaNominalAA / 100.0
        return (1.0 + iNom / diasNoAno).pow(diasNoAno.toDouble()) - 1.0
    }

}