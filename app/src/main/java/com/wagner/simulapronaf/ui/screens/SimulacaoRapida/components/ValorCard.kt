package com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.DoNotDisturbOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.wagner.simulapronaf.ui.components.shared.DivisorHorizontal
import com.wagner.simulapronaf.ui.components.shared.IconeLegenda
import com.wagner.simulapronaf.ui.components.shared.SliderDecimal
import com.wagner.simulapronaf.ui.components.shared.TituloCard
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo
import java.text.NumberFormat
import java.util.Locale

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
                TituloCard(texto = "Valor")
                DivisorHorizontal()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    IconeLegenda(
                        icone = Icons.Outlined.DoNotDisturbOn,
                        descricao = "Subtrair",
                        texto = "1 mil"
                    )

                    SliderDecimal(
                        valor = valorSimulacao,
                        onValorChange = onValorChange,
                        faixa = 1000f..300000f,
                        passo = 500f,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )


                    IconeLegenda(
                        icone = Icons.Outlined.AddCircleOutline,
                        descricao = "Adcionar",
                        texto = "250 mil"
                    )
                }

                Text(
                    text = formatarValor(valorSimulacao.toInt()),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
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
    var valorSimulado by remember { mutableStateOf(1000f) }
    Column(modifier = Modifier.fillMaxSize()) {
        ValorCard(
            valorSimulacao = valorSimulado,
            onValorChange = { valorSimulado = it })
    }
}