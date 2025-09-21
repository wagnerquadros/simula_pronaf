package com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.wagner.simulapronaf.ui.screens.sharedComponents.DivisorHorizontal
import com.wagner.simulapronaf.ui.screens.sharedComponents.IconeLegenda
import com.wagner.simulapronaf.ui.screens.sharedComponents.SliderDecimal
import com.wagner.simulapronaf.ui.screens.sharedComponents.TituloCard
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo

@Composable
fun TaxaCard(
    taxa: Float,
    onValorChange: (Float) -> Unit,
    modifier: Modifier = Modifier.padding(16.dp)
) {


    Card(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CorDoCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                TituloCard(texto = "Taxa")
                DivisorHorizontal()

                Text(
                    text = taxa.toString() + " %",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdePetroleo
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconeLegenda(
                        icone = Icons.Outlined.DoNotDisturbOn,
                        descricao = "Subtrair",
                        texto = "1"
                    )

                    SliderDecimal(
                        valor = taxa,
                        onValorChange = { onValorChange(it) },
                        faixa = 1.0f..8.0f,
                        passo = 0.5f,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )

                    IconeLegenda(
                        icone = Icons.Outlined.AddCircleOutline,
                        descricao = "Adcionar",
                        texto = "8"
                    )
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ParcelasCardPreview() {
    var taxa by remember { mutableStateOf(1.0f) }
    Column(modifier = Modifier.fillMaxWidth()) {
        TaxaCard(
            taxa = taxa,
            onValorChange = { taxa = it })
    }
}