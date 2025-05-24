package com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.DoNotDisturbOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.screens.sharedComponents.DivisorHorizontal
import com.wagner.simulapronaf.ui.screens.sharedComponents.IconeLegenda
import com.wagner.simulapronaf.ui.screens.sharedComponents.ModalidadeDrop
import com.wagner.simulapronaf.ui.screens.sharedComponents.ParcelasSlider
import com.wagner.simulapronaf.ui.screens.sharedComponents.TituloCard
import com.wagner.simulapronaf.ui.theme.CinzaClaroTextoSecundario
import com.wagner.simulapronaf.ui.theme.CinzaTextoPrimario
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo
import kotlin.math.roundToInt

@Composable
fun ParcelasCard() {
    var parcelas by remember { mutableStateOf(1) }
    var modalidade by remember { mutableStateOf("Anual") }

    Card(
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = CorDoCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TituloCard(texto = "Parcelas")
                DivisorHorizontal()

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        modifier = Modifier,
                        text = "Modalidade da parcela",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = CinzaClaroTextoSecundario
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    ModalidadeDrop(
                        opcoes = listOf("Anual", "Semestral"),
                        valorSelecionado = modalidade,
                        onSelecionar = { modalidade = it },
                        modifier = Modifier,
                    )
                }

                DivisorHorizontal()
                Spacer(modifier = Modifier.padding(10.dp))
                TituloCard(texto = "Quantidade", textAlign = TextAlign.Center)


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconeLegenda(
                        icone = Icons.Outlined.DoNotDisturbOn,
                        descricao = "Subtrair",
                        texto = "1"
                    )

                    ParcelasSlider(
                        valor = parcelas,
                        onValorChange = { parcelas = it },
                        faixa = 1..10,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )

                    IconeLegenda(
                        icone = Icons.Outlined.AddCircleOutline,
                        descricao = "Adcionar",
                        texto = "10"
                    )
                }

                Text(
                    text = parcelas.toString(),
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

@Preview(showBackground = true)
@Composable
private fun ParcelasCardPreview() {
    Column(modifier = Modifier.fillMaxWidth()) { ParcelasCard() }
}