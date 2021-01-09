package com.milkyway.milkyway.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "data_store"
    )

    val getNickname: Flow<String?> = dataStore.data.map { preferences ->
        preferences[nicknameKey]?:""
    }

    suspend fun setNickname(nickname: String) {
        dataStore.edit { preferences ->
            preferences[nicknameKey] = nickname
        }
    }

    companion object {
        val nicknameKey = preferencesKey<String>("nickname")
    }
}