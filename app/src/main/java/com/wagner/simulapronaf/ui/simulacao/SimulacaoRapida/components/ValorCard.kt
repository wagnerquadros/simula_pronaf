package com.wagner.simulapronaf.ui.simulacao.SimulacaoRapida.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.components.DivisorHorizontal
import com.wagner.simulapronaf.ui.components.TituloCard
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ValorCard(
    valorSimulacao: Float,
    onValorChange: (Float) -> Unit,
    modifier: Modifier = Modifier.padding(16.dp),
    minimo: Int = 1_000,
    maximo: Int = 250_000,
    isErro: Boolean = false,
    mensagemErro: String? = null,
    focusRequester: FocusRequester,
    maxDigitos: Int = 6
) {

    var campo by remember {
        mutableStateOf(TextFieldValue(text = "", selection = TextRange(0)))
    }

    LaunchedEffect(valorSimulacao) {
        val t = if (valorSimulacao > 0) formatarValor(valorSimulacao.toInt()) else ""
        campo = TextFieldValue(text = t, selection = TextRange(t.length))
    }

    Card(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 0.dp),
        colors = CardDefaults.cardColors(containerColor = CorDoCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            TituloCard(texto = "Valor")
            DivisorHorizontal()
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = campo,
                onValueChange = { novo ->
                    val digitosNovos = novo.text.filter { it.isDigit() }
                    if (digitosNovos.length > maxDigitos) return@TextField

                    if (digitosNovos.isEmpty()) {
                        campo = TextFieldValue("", selection = TextRange(0))
                        onValorChange(0f)
                        return@TextField
                    }

                    val bruto = digitosNovos.toLongOrNull() ?: 0L
                    val formatado = formatarValor(bruto.toInt())

                    campo = TextFieldValue(
                        text = formatado,
                        selection = TextRange(formatado.length)
                    )
                    onValorChange(bruto.toFloat())
                },

                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdePetroleo
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                isError = isErro,
                supportingText = {
                    Text(
                        text = mensagemErro ?: "Faixa: ${formatarValor(minimo)} a ${formatarValor(maximo)}"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = VerdePetroleo,
                    unfocusedTextColor = VerdePetroleo,
                    focusedIndicatorColor = VerdePetroleo,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedPrefixColor = VerdePetroleo,
                    unfocusedPrefixColor = VerdePetroleo,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedPlaceholderColor = Color.LightGray,
                ),
                prefix = {
                    Text(
                        text = "R$ ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = VerdePetroleo
                    )
                },

                placeholder = {
                    Text(
                        text = "Digite o valor aqui",
                        fontSize = 20.sp,
                        color = Color.LightGray
                    )
                }
            )
        }
    }
}

fun formatarValor(valor: Number): String {
    val formatoBR = NumberFormat.getNumberInstance(Locale("pt", "BR"))
    return "${formatoBR.format(valor)}"
}


@Preview(showBackground = true)
@Composable
fun ValorCardPreviewEstados() {
    var valorOk by remember { mutableStateOf(10_000f) }
    var valorErro by remember { mutableStateOf(300_000f) } // fora da faixa para demonstrar erro

    val focoOk = remember { FocusRequester() }
    val focoErro = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ValorCard(
            valorSimulacao = valorOk,
            onValorChange = { valorOk = it },
            minimo = 1_000,
            maximo = 250_000,
            isErro = false,
            mensagemErro = null,
            focusRequester = focoOk
        )


        ValorCard(
            valorSimulacao = valorErro,
            onValorChange = { valorErro = it },
            minimo = 1_000,
            maximo = 250_000,
            isErro = true,
            mensagemErro = "Informe um valor entre R$ 1.000 e R$ 250.000",
            focusRequester = focoErro
        )
    }
}