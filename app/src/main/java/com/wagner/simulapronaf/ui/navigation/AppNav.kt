package com.wagner.simulapronaf.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wagner.simulapronaf.ui.screens.Cronograma.CronogramaHost
import com.wagner.simulapronaf.ui.screens.SimulacaoRapida.SimulacaoRapidaScreen

@Composable
fun AppNav() {

    val nav = rememberNavController()

    val vmCompartilhado: SimulacaoViewModel = viewModel()

    NavHost(navController = nav, startDestination = "entrada") {
        composable("entrada") {
            SimulacaoRapidaScreen(
                onSimularClick = { nav.navigate("cronograma") },
                vmCompartilhado = vmCompartilhado
            )
        }
        composable("cronograma") {
            CronogramaHost(vmCompartilhado = vmCompartilhado)
        }
    }
}