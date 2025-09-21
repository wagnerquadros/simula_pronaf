package com.wagner.simulapronaf.domain.service.utils.politicas

import com.wagner.simulapronaf.domain.models.Parcela

interface AjusteResiduo {
    fun ajustar(parcela: Parcela, arredondar: (Double) -> Double): Parcela
}