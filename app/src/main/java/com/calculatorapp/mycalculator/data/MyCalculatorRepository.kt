package com.calculatorapp.mycalculator.data

import net.objecthunter.exp4j.ExpressionBuilder

class MyCalculatorRepository {
    fun calculate(expression: String): Double {
        return try {
            val expression = ExpressionBuilder(expression).build()
            expression.evaluate()
        } catch (e: Exception) {
            Double.NaN
        }
    }
}
