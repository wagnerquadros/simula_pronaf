package com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.DoNotDisturbOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
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
import com.wagner.simulapronaf.ui.components.shared.ModalidadeDrop
import com.wagner.simulapronaf.ui.components.shared.SliderValorInteiro
import com.wagner.simulapronaf.ui.components.shared.TituloCard
import com.wagner.simulapronaf.ui.theme.CinzaClaroTextoSecundario
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo

@Composable
fun ParcelasCard(
    parcelas: Int,
    onParcelasChange: (Int) -> Unit,
    modalidade: String,
    onModalidadeChange: (String) -> Unit,
    modifier: Modifier = Modifier.padding(16.dp)
) {

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

                Column {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            modifier = Modifier,
                            text = "Carência",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = CinzaClaroTextoSecundario
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        ModalidadeDrop(
                            opcoes = listOf("Sem carência", "01 ano", "02 anos", "03 anos"),
                            valorSelecionado = modalidade,
                            onSelecionar = onModalidadeChange,
                            modifier = Modifier,
                        )
                    }


                }


                DivisorHorizontal()
                Spacer(modifier = Modifier.padding(8.dp))
                TituloCard(texto = "Prazo", textAlign = TextAlign.Center)


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

                    SliderValorInteiro(
                        valor = parcelas,
                        onValorChange = { onParcelasChange(it) },
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
                    text = parcelas.toString() + " ano(s)",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
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
    var parcelas by remember { mutableStateOf(1) }
    var carencia by remember { mutableStateOf(1) }
    var modalidade by remember { mutableStateOf("Anual") }
    Column(modifier = Modifier.fillMaxWidth()) {
        ParcelasCard(
            parcelas = parcelas,
            onParcelasChange = { parcelas = it },
            modalidade = modalidade,
            onModalidadeChange = { modalidade = it })
    }
}