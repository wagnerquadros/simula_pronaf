package com.wagner.simulapronaf.ui.SplashScreen


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wagner.simulapronaf.R
import com.wagner.simulapronaf.ui.theme.VerdePetroleo
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {

    var logoVisivel by remember { mutableStateOf(false) }

    val alphaLogo by animateFloatAsState(
        targetValue = if (logoVisivel) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500
        ),
        label = "alphaLogo"
    )

    LaunchedEffect(true) {
        logoVisivel = true
        delay(2000)
        navController.navigate("entrada") {
            popUpTo(route = "splash") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VerdePetroleo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.alpha(alphaLogo)
        )
    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        navController = rememberNavController(
        )
    )
}