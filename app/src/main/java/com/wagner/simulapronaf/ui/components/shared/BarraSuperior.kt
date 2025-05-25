package com.wagner.simulapronaf.ui.components.shared

import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagner.simulapronaf.ui.theme.VerdePetroleo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(onSairClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier.height(60.dp),
        title = {
            Text(
                text = "SimulaPRONAF",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        },
        actions = {
            IconButton(onClick = onSairClick,
               modifier = Modifier.padding(top = 4.dp)
            ) {
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
