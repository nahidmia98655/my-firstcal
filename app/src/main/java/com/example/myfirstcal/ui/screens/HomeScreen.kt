package com.example.myfirstcal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstcal.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

/**
 * Main calculator screen.
 */
@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit,
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator") },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Display
            Text(
                text = state.display,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.Bottom)
                    .padding(8.dp),
                maxLines = 1
            )
            // Buttons grid
            val buttonRows = listOf(
                listOf("C", "⌫", "/", "*"),
                listOf("7", "8", "9", "-"),
                listOf("4", "5", "6", "+"),
                listOf("1", "2", "3", "="),
                listOf("0", ".", "", "")
            )
            buttonRows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { label ->
                        CalculatorButton(
                            label = label,
                            onClick = { viewModel.onButtonClick(label) },
                            modifier = Modifier
                                .weight(
                                    when (label) {
                                        "0" -> 2f
                                        else -> 1f
                                    }
                                )
                                .height(64.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Reusable button composable for calculator keys.
 */
@Composable
fun CalculatorButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Empty placeholders (e.g., blanks) are not clickable
    if (label.isBlank()) {
        Spacer(modifier = modifier)
        return
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = when (label) {
                "C", "⌫" -> Color(0xFFFF5252) // red for clear and backspace
                "=", "/", "*", "-", "+" -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.surface
            },
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = 20.sp
        )
    }
}