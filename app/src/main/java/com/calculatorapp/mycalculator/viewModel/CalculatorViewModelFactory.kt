package com.calculatorapp.mycalculator.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calculatorapp.mycalculator.data.MyCalculatorRepository
import com.calculatorapp.mycalculator.data.DataStoreManager

class CalculatorViewModelFactory(
    private val repository: MyCalculatorRepository,
    private val dataStore: DataStoreManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyCalculatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyCalculatorViewModel(repository, dataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
