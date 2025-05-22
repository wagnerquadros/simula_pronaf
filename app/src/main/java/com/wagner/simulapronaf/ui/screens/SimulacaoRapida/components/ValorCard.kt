package com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.DoNotDisturbOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.theme.CinzaTextoPrimario
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun ValorCard(
    valorSimulacao: Float,
    onValorChange: (Float) -> Unit,
    modifier: Modifier = Modifier.padding(16.dp)

) {

    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = CorDoCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Valor",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = CinzaTextoPrimario
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    thickness = 1.2.dp,
                    color = CinzaTextoPrimario
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DoNotDisturbOn,
                            contentDescription = "Remover",
                            tint = CinzaTextoPrimario,
                            modifier = Modifier.padding(top = 25.dp)
                        )

                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            text = "1 mil",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = CinzaTextoPrimario,
                            textAlign = TextAlign.Center
                        )
                    }

                    Slider(
                        value = valorSimulacao,
                        onValueChange = {
                            val arredondado = (it / 100).roundToInt() * 100f
                            onValorChange(arredondado)
                        },
                        valueRange = 1000f..300000f,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = VerdePetroleo,
                            activeTrackColor = VerdePetroleo,
                            inactiveTrackColor = CinzaTextoPrimario
                        )
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddCircleOutline,
                            contentDescription = "Adicionar",
                            tint = CinzaTextoPrimario,
                            modifier = Modifier.padding(top = 25.dp)
                        )

                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            text = "300 mil",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = CinzaTextoPrimario,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                val valorFormatado = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                    .format(valorSimulacao.toInt())

                Text(
                    text = formatarValor(valorSimulacao.toInt()),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdePetroleo
                )
            }
        }
    )
}

fun formatarValor(valor: Number): String {
    val formatoBR = NumberFormat.getNumberInstance(Locale("pt", "BR"))
    return "R$ ${formatoBR.format(valor)}"
}


@Preview(showBackground = true)
@Composable
private fun ValorCardPreview() {
    var valorSimulado by remember { mutableStateOf(1000f)}
    Column(modifier = Modifier.fillMaxSize()) {
        ValorCard(
            valorSimulacao = valorSimulado,
            onValorChange = { valorSimulado = it })
    }
}