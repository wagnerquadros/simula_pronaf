package com.wagner.simulapronaf.ui.components

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.wagner.simulapronaf.ui.theme.VerdePetroleo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(onSairClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "SimulaPRONAF",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = onSairClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Sair",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = VerdePetroleo,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun BarraSuperiorPreview() {
    val context = LocalContext.current
    BarraSuperior(
        onSairClick = {
            Toast.makeText(context, "Você saiu!", Toast.LENGTH_SHORT).show()
        }
    )
}
