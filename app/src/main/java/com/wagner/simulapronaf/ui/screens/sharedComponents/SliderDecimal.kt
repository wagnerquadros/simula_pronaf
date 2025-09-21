package com.wagner.simulapronaf.ui.screens.sharedComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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


@Preview(showBackground = true)
@Composable
fun SliderDecimalPreview() {
    var valor by remember { mutableStateOf(3.0f) }

    Column(modifier = Modifier.fillMaxWidth()) {
        SliderDecimal(
            valor = valor,
            onValorChange = { valor = it },
            faixa = 1f..6f,
            passo = 0.5f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}