package com.calculatorapp.mycalculator.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calculatorapp.mycalculator.data.MyCalculatorRepository
import com.calculatorapp.mycalculator.data.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyCalculatorViewModel(private val repository: MyCalculatorRepository, private val dataStore: DataStoreManager) : ViewModel() {

    private val _expression = MutableStateFlow("")
    val expression: StateFlow<String> = _expression

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> = _result

    private val _history = MutableStateFlow("")
    val history: StateFlow<String> = _history

    init {
        viewModelScope.launch {
            dataStore.history.collect {
                _history.value = it
            }
        }
    }

    fun onInput(value: String) {
        _expression.value += value
    }

    fun onClear() {
        _expression.value = ""
        _result.value = "0"
    }

    fun onCalculate() {
        val result = repository.calculate(_expression.value)
        _result.value = result.toString()
        saveToHistory("${_expression.value} = $result")
        _expression.value = ""
    }

    private fun saveToHistory(entry: String) {
        val newHistory = "$entry\n${_history.value}"
        viewModelScope.launch {
            dataStore.saveHistory(newHistory)
        }
    }
}
