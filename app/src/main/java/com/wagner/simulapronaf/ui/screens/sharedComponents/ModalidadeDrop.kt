package com.wagner.simulapronaf.ui.screens.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagner.simulapronaf.ui.theme.CinzaClaroTextoSecundario

@Composable
fun ModalidadeDrop(
    opcoes: List<String>,
    valorSelecionado: String,
    onSelecionar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .width(120.dp)
            .shadow(8.dp, shape = RoundedCornerShape(30.dp))
            .height(40.dp)
            .clickable { expanded = true }
            .background(Color.White, shape = RoundedCornerShape(30.dp))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart,

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = valorSelecionado,
                fontSize = 14.sp,
                color = CinzaClaroTextoSecundario
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = CinzaClaroTextoSecundario
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(90.dp)
                .background(Color.White),
            tonalElevation = 10.dp
        ) {
            opcoes.forEach { opcao ->
                DropdownMenuItem(
                    text = {
                        Text(
                            opcao,
                            color = CinzaClaroTextoSecundario,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    onClick = {
                        onSelecionar(opcao)
                        expanded = false
                    }
                )
            }
        }
    }
}