package com.ddd.carssok.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarssokDataStoreImpl @Inject constructor(
    private val userPreference: DataStore<Preferences>
) : CarssokDataStore {

    override fun getUserName(): Flow<String> = userPreference.data.map {
        it[stringPreferencesKey(KEY_USER_NAME)] ?: ""
    }

    override suspend fun updateUserName(name: String) {
        userPreference.edit {
            it[stringPreferencesKey(KEY_USER_NAME)] = name
        }
    }

    override fun clear() {
        // TODO
    }

    companion object {
        const val PREFERENCE_DATA_STORE_NAME = "carssok_preferences.pb"
        private const val KEY_USER_NAME = "key_user_name"
    }
}