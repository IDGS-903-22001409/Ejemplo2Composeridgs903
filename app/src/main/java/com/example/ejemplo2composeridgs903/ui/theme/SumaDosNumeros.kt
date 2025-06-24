package com.example.ejemplo2composeridgs903.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SumaDosNumeros() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val radioOptions = listOf("Suma", "Resta", "Multiplicación", "División")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Column(modifier = Modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { selectedOption = text },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Button(onClick = {
            val n1 = num1.toFloatOrNull()
            val n2 = num2.toFloatOrNull()
            resultado = if (n1 != null && n2 != null) {
                when (selectedOption) {
                    "Suma" -> "Resultado: ${n1 + n2}"
                    "Resta" -> "Resultado: ${n1 - n2}"
                    "Multiplicación" -> "Resultado: ${n1 * n2}"
                    "División" -> {
                        if (n2 != 0f) "Resultado: ${n1 / n2}"
                        else "Error: División entre 0"
                    }
                    else -> "Operación no válida"
                }
            } else {
                "Error: Ingresa números válidos"
            }
        }) {
            Text("Calcular")
        }

        Text(text = resultado)
    }
}