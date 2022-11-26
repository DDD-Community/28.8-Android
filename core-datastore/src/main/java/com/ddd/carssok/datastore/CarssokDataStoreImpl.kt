package com.ddd.carssok.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarssokDataStoreImpl @Inject constructor(
    private val carssokPreferences: DataStore<Preferences>
) : CarssokDataStore {

    override fun getUserName(): Flow<String> = carssokPreferences.data.map { preferences ->
        preferences[KEY_USER_NAME] ?: ""
    }

    override suspend fun updateUserName(name: String) {
        carssokPreferences.edit { preferences ->
            preferences[KEY_USER_NAME] = name
        }
    }

    override fun clear() {
        // TODO
    }

    companion object {
        const val PREFERENCE_DATA_STORE_NAME = "carssok_preferences.pb"
        private val KEY_USER_NAME = "key_user_name".toKey()

        private fun String.toKey(): Preferences.Key<String> = stringPreferencesKey(this)
    }
}