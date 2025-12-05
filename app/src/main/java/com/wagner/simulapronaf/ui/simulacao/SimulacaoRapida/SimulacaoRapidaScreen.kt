package com.wagner.simulapronaf.ui.simulacao.SimulacaoRapida

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagner.simulapronaf.ui.components.BarraSuperior
import com.wagner.simulapronaf.ui.components.BotaoPrimario
import com.wagner.simulapronaf.ui.simulacao.SimulacaoRapida.components.ParcelasCard
import com.wagner.simulapronaf.ui.simulacao.SimulacaoRapida.components.TaxaCard
import com.wagner.simulapronaf.ui.simulacao.SimulacaoRapida.components.ValorCard
import com.wagner.simulapronaf.ui.simulacao.SimulacaoViewModel

@Composable
fun SimulacaoRapidaScreen(
    onSimularClick: () -> Unit,
    simulacaoViewModel: SimulacaoViewModel
) {

    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val scroll = rememberScrollState()
    val minimo = 1_000
    val maximo = 250_000
    val estado = simulacaoViewModel.estado
    val focoValor = remember { FocusRequester() }

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
                valorSimulacao = estado.valorSimulacao,
                onValorChange = { novoValor ->
                    simulacaoViewModel.aoMudarValorSimulacao(novoValor)
                    if (novoValor.toInt() in minimo..maximo) simulacaoViewModel.limparErroValor()
                },
                minimo = minimo,
                maximo = maximo,
                isErro = estado.erroValor != null,
                mensagemErro = estado.erroValor,
                focusRequester = focoValor

            )

            ParcelasCard(
                parcelas = estado.parcelas,
                onParcelasChange = { simulacaoViewModel.aoMudarParcelas(it) },
                modalidade = estado.modalidade,
                onModalidadeChange = { simulacaoViewModel.aoMudarModalidade(it) }
            )

            TaxaCard(
                taxa = estado.taxa,
                onValorChange = { simulacaoViewModel.aoMudarTaxa(it) }
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BotaoPrimario(
                    texto = "Simular",
                    onClick = {
                        val sucesso = simulacaoViewModel.tentarSimular(minimo, maximo)

                        if (!sucesso) {
                            val mensagem = simulacaoViewModel.estado.erroValor
                            focoValor.requestFocus()
                            mensagem?.let {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }
                            return@BotaoPrimario
                        }
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
        simulacaoViewModel = vm
    )
}