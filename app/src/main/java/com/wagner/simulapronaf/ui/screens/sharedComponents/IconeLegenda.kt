package com.wagner.simulapronaf.ui.screens.sharedComponents

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.theme.CinzaTextoPrimario

@Composable
fun IconeLegenda(
    icone: ImageVector,
    descricao: String,
    texto: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentWidth()
    ) {
        Icon(
            imageVector = icone,
            contentDescription = descricao,
            tint = CinzaTextoPrimario,
            modifier = Modifier.padding(top = 25.dp)
        )

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = texto,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = CinzaTextoPrimario,
            textAlign = TextAlign.Center
        )
    }
}
