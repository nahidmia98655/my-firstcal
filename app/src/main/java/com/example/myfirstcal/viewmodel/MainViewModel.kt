package com.example.myfirstcal.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * UI state representing the calculator display.
 */
@Immutable
data class CalculatorUiState(
    val display: String = "0"
)

/**
 * ViewModel handling calculator logic.
 */
class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState

    // Internal variables for calculation logic
    private var operand1: Double? = null
    private var pendingOperation: String? = null
    private var isNewInput = true

    /**
     * Handles button clicks from the UI.
     */
    fun onButtonClick(label: String) {
        when (label) {
            "C" -> clear()
            "⌫" -> backspace()
            "=" -> calculateResult()
            "/", "*", "-", "+" -> setOperation(label)
            else -> appendInput(label)
        }
    }

    private fun clear() {
        _uiState.update { it.copy(display = "0") }
        operand1 = null
        pendingOperation = null
        isNewInput = true
    }

    private fun backspace() {
        val current = _uiState.value.display
        if (current.isNotEmpty() && current != "0") {
            val newText = if (current.length == 1) "0" else current.dropLast(1)
            _uiState.update { it.copy(display = newText) }
        }
    }

    private fun appendInput(label: String) {
        val current = _uiState.value.display
        val newText = if (isNewInput || current == "0") {
            if (label == ".") "0." else label
        } else {
            // Prevent multiple dots in a number
            if (label == "." && current.contains('.')) current else current + label
        }
        _uiState.update { it.copy(display = newText) }
        isNewInput = false
    }

    private fun setOperation(op: String) {
        val current = _uiState.value.display
        operand1 = current.toDoubleOrNull()
        pendingOperation = op
        isNewInput = true
    }

    private fun calculateResult() {
        val operand2 = _uiState.value.display.toDoubleOrNull()
        val op1 = operand1
        val operation = pendingOperation
        if (op1 != null && operand2 != null && operation != null) {
            val result = when (operation) {
                "+" -> op1 + operand2
                "-" -> op1 - operand2
                "*" -> op1 * operand2
                "/" -> if (operand2 != 0.0) op1 / operand2 else Double.NaN
                else -> Double.NaN
            }
            val resultText = if (result % 1.0 == 0.0) {
                result.toLong().toString()
            } else {
                result.toString()
            }
            _uiState.update { it.copy(display = resultText) }
            // Reset for next calculation
            operand1 = null
            pendingOperation = null
            isNewInput = true
        }
    }
}