package com.wagner.simulapronaf.ui.components.shared

import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wagner.simulapronaf.ui.theme.CinzaTextoPrimario
import com.wagner.simulapronaf.ui.theme.VerdePetroleo
import kotlin.math.roundToInt

@Composable
fun SliderDecimal(
    valor: Float,
    onValorChange: (Float) -> Unit,
    faixa: ClosedFloatingPointRange<Float>,
    passo: Float = 100f,
    modifier: Modifier = Modifier
) {
    Slider(
        value = valor,
        onValueChange = {
            val arredondado = (it / passo).roundToInt() * passo
            onValorChange(arredondado)
        },
        valueRange = faixa,
        modifier = modifier,
        colors = SliderDefaults.colors(
            thumbColor = VerdePetroleo,
            activeTrackColor = VerdePetroleo,
            inactiveTrackColor = CinzaTextoPrimario
        )
    )
}