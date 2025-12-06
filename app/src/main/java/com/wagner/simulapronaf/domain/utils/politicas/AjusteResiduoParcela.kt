package com.wagner.simulapronaf.domain.utils.politicas

import com.wagner.simulapronaf.domain.models.Parcela

class AjusteResiduoParcela : AjusteResiduo{

    override fun ajustar(parcela: Parcela, arredondar: (Double) -> Double): Parcela {
        val residuo = parcela.saldoDevedor
        val novoCapital = arredondar(parcela.capitalPago + residuo)
        val novaParcela = arredondar(parcela.valorParcela + residuo)
        return parcela.copy(
            capitalPago = novoCapital,
            valorParcela = novaParcela,
            saldoDevedor = 0.0
        )
    }

}