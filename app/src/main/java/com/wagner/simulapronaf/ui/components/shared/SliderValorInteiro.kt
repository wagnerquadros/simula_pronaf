package com.wagner.simulapronaf.ui.components.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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


@Preview(showBackground = true)
@Composable
fun SliderValorInteiroPreview() {
    var valor by remember { mutableStateOf(5) }

    Column(modifier = Modifier.fillMaxWidth()) {

        SliderValorInteiro(
            valor = valor,
            onValorChange = { valor = it },
            faixa = 1..10,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

