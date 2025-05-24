package com.wagner.simulapronaf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.ParcelasCard
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.components.ValorCard


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var valorSimulacao by remember { mutableStateOf(1000f) }

            Column {
                ValorCard(
                    valorSimulacao = valorSimulacao,
                    onValorChange = { valorSimulacao = it }
                )

                ParcelasCard()
            }



        }
    }
}



