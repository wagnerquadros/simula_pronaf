package com.wagner.simulapronaf.ui.screens.SimulacaoRapida

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagner.simulapronaf.domain.models.ModalidadeEnum
import com.wagner.simulapronaf.domain.models.SimulacaoEntrada
import com.wagner.simulapronaf.domain.models.carenciaFromString
import com.wagner.simulapronaf.domain.service.impl.SimulacaoService
import com.wagner.simulapronaf.navigation.SimulacaoViewModel
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.ParcelasCard
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.TaxaCard
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.ValorCard
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.formatarValor
import com.wagner.simulapronaf.ui.screens.sharedComponents.BarraSuperior
import com.wagner.simulapronaf.ui.screens.sharedComponents.BotaoPrimario

@Composable
fun SimulacaoRapidaScreen(
    onSimularClick: () -> Unit,
    vmCompartilhado: SimulacaoViewModel
) {

    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val scroll = rememberScrollState()

    val minimo = 1_000
    val maximo = 250_000

    var valorSimulacao by rememberSaveable { mutableStateOf(0f) }
    var parcelas by remember { mutableStateOf(1) }
    var taxa by remember { mutableStateOf(1.0f) }
    var modalidade by remember { mutableStateOf("Sem carência") }

    val focoValor = remember { androidx.compose.ui.focus.FocusRequester() }
    var erroValor: String? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            BarraSuperior(onSairClick = { activity?.finish() })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scroll),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            ValorCard(
                valorSimulacao = valorSimulacao,
                onValorChange = {
                    valorSimulacao = it
                    if (it.toInt() in minimo..maximo) erroValor = null
                },
                minimo = minimo,
                maximo = maximo,
                isErro = erroValor != null,
                mensagemErro = erroValor,
                focusRequester = focoValor

            )

            ParcelasCard(
                parcelas = parcelas,
                onParcelasChange = { parcelas = it },
                modalidade = modalidade,
                onModalidadeChange = { modalidade = it }
            )

            TaxaCard(
                taxa = taxa,
                onValorChange = { taxa = it }
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BotaoPrimario(
                    texto = "Simular",
                    onClick = {

                        val valorInt = valorSimulacao.toInt()
                        if (valorInt !in minimo..maximo) {
                            erroValor = "Informe um valor entre ${formatarValor(minimo)} e ${formatarValor(maximo)}"
                            focoValor.requestFocus()
                            Toast.makeText(context, erroValor, Toast.LENGTH_SHORT).show()
                            return@BotaoPrimario
                        }

                        val entrada = SimulacaoEntrada(
                            valorSimulacao = valorSimulacao.toDouble(),
                            prazo = parcelas,
                            taxaDeJuros = taxa.toDouble(),
                            carencia = carenciaFromString(modalidade),
                            modalidadeParcela = ModalidadeEnum.ANUAL
                        )

                        val service = SimulacaoService()
                        val resultado = service.criarSimulacao(entrada)
                        vmCompartilhado.atualizarResultado(resultado)
                        onSimularClick()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimulacaoRapidaScreenPreview() {
    val vm = remember { SimulacaoViewModel() }
    SimulacaoRapidaScreen(
        onSimularClick = { /* no-op preview */ },
        vmCompartilhado = vm
    )
}