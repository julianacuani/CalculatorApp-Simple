package com.calculatorapp.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.calculatorapp.mycalculator.data.MyCalculatorRepository
import com.calculatorapp.mycalculator.data.DataStoreManager
import com.calculatorapp.mycalculator.ui.components.CalculatorScreen
import com.calculatorapp.mycalculator.viewModel.MyCalculatorViewModel
import com.calculatorapp.mycalculator.viewModel.CalculatorViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = MyCalculatorRepository()
        val dataStore = DataStoreManager(applicationContext)

        val factory = CalculatorViewModelFactory(repository, dataStore)

        val viewModel = ViewModelProvider(this, factory).get(MyCalculatorViewModel::class.java)

        setContent {
            CalculatorScreen(viewModel = viewModel)
        }
    }
}
