package com.wagner.simulapronaf.ui.screens.Cronograma.components


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.R
import com.wagner.simulapronaf.ui.components.shared.TextoResumoDetalhe

import com.wagner.simulapronaf.ui.theme.AzulBotao
import com.wagner.simulapronaf.ui.theme.CorDoCard
import com.wagner.simulapronaf.ui.theme.VerdePetroleo


@Composable
fun DetalharCardExpandable(
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val rotation by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Card(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = CorDoCard),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        content = {


            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
            ) {
                if (expanded) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                    ) {
                        TextoResumoDetalhe("Capital", "20.000,00", fontSize = 12.sp)
                        TextoResumoDetalhe("Capital", "20.000,00", fontSize = 12.sp)
                        TextoResumoDetalhe("Capital", "20.000,00", fontSize = 12.sp)
                    }
                }
            }

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (expanded) "Ocultar" else "Detalhar",
                        modifier = Modifier.padding(4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = VerdePetroleo
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = null,
                        tint = VerdePetroleo,
                        modifier = Modifier
                            .rotate(rotation)
                    )

                }
            }


        }
    )
}


@Preview(showBackground = true)
@Composable
private fun DetalharCardExpandablePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetalharCardExpandable()
    }
}