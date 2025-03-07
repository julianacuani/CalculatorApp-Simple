package com.calculatorapp.mycalculator.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.calculatorapp.mycalculator.data.DataStoreManager
import com.calculatorapp.mycalculator.data.MyCalculatorRepository
import com.calculatorapp.mycalculator.viewModel.MyCalculatorViewModel

@Composable
fun CalculatorScreen(viewModel: MyCalculatorViewModel = viewModel()) {
    val expression by viewModel.expression.collectAsState()
    val result by viewModel.result.collectAsState()
    val history by viewModel.history.collectAsState()

    var isHistoryVisible by remember { mutableStateOf(false) }

    Column {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Red)
        ) {
            Text(
                text = expression,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = result,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            val buttonLabels = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("C", "0", "=", "+")
            )

            buttonLabels.forEach { rowLabels ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowLabels.forEach { label ->
                        CalculatorButton(label) {
                            when (label) {
                                "C" -> viewModel.onClear()
                                "=" -> viewModel.onCalculate()
                                else -> viewModel.onInput(label)
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .clickable { isHistoryVisible = !isHistoryVisible },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "History",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Icon(
                imageVector = if (isHistoryVisible) {
                    Icons.Default.KeyboardArrowUp
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                contentDescription = "Toggle History",
                tint = Color.Red
            )
        }

        // Exibir o histórico se estiver visível
        if (isHistoryVisible) {
            Text(
                text = history,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black // Cor do texto do histórico
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    val viewModel = MyCalculatorViewModel(
        MyCalculatorRepository(),
        DataStoreManager(LocalContext.current)
    )
    CalculatorScreen(viewModel)
}