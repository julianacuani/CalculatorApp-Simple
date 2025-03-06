package com.calculatorapp.mycalculator.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("calculator_history")

class DataStoreManager(private val context: Context) {

    companion object {
        val HISTORY_KEY = stringPreferencesKey("history")
    }

    val history: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[HISTORY_KEY] ?: "" }

    suspend fun saveHistory(history: String) {
        context.dataStore.edit { preferences ->
            preferences[HISTORY_KEY] = history
        }
    }
}
