package com.wagner.simulapronaf.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wagner.simulapronaf.ui.simulacao.Cronograma.CronogramaHost
import com.wagner.simulapronaf.ui.simulacao.SimulacaoRapida.SimulacaoRapidaScreen
import com.wagner.simulapronaf.ui.SplashScreen.SplashScreen
import com.wagner.simulapronaf.ui.simulacao.SimulacaoViewModel

@Composable
fun AppNav() {

    val nav = rememberNavController()
    val vmCompartilhado: SimulacaoViewModel = viewModel()

    NavHost(navController = nav, startDestination = "splash") {
        composable(route = "splash") {
            SplashScreen(navController = nav)
        }

        composable("entrada") {
            SimulacaoRapidaScreen(
                onSimularClick = { nav.navigate("cronograma") },
                simulacaoViewModel = vmCompartilhado
            )
        }
        composable("cronograma") {
            CronogramaHost(simulacaoViewModel = vmCompartilhado)
        }
    }
}