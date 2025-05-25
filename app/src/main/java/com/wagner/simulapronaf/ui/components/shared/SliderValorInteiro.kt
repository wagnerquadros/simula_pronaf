package com.wagner.simulapronaf.ui.components.shared

import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wagner.simulapronaf.ui.theme.CinzaTextoPrimario
import com.wagner.simulapronaf.ui.theme.VerdePetroleo
import kotlin.math.roundToInt

@Composable
fun SliderValorInteiro(
    valor: Int,
    onValorChange: (Int) -> Unit,
    faixa: IntRange,
    modifier: Modifier = Modifier
) {
    val steps = faixa.last - faixa.first - 1

    Slider(
        value = valor.toFloat(),
        onValueChange = {
            onValorChange(it.roundToInt().coerceIn(faixa))
        },
        valueRange = faixa.first.toFloat()..faixa.last.toFloat(),
        steps = steps,
        modifier = modifier,
        colors = SliderDefaults.colors(
            thumbColor = VerdePetroleo,
            activeTrackColor = VerdePetroleo,
            inactiveTrackColor = CinzaTextoPrimario
        )
    )
}