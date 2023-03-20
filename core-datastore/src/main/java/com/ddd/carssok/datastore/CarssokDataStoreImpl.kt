package com.ddd.carssok.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarssokDataStoreImpl @Inject constructor(
    private val carssokPreferences: DataStore<Preferences>
) : CarssokDataStore {

    override fun getUserName(): Flow<String> = carssokPreferences.data.map { preferences ->
        preferences[KEY_USER_NAME].orEmpty()
    }

    override suspend fun updateUserName(name: String) {
        carssokPreferences.edit { preferences ->
            preferences[KEY_USER_NAME] = name
        }
    }

    override fun getUserToken(): Flow<String> {
        return carssokPreferences.data.map { it[KEY_USER_TOKEN].orEmpty() }
    }

    override suspend fun updateUserToken(token: String) {
        carssokPreferences.edit { preferences ->
            preferences[KEY_USER_TOKEN] = token
        }
    }

    override fun checkedUserAgreement(): Flow<Boolean> {
        return carssokPreferences.data.map { it[KEY_USER_AGREEMENT] ?: false }
    }

    override suspend fun saveUserAgreement() {
        carssokPreferences.edit { preferences ->
            preferences[KEY_USER_AGREEMENT] = true
        }
    }

    override fun clear() {
        // TODO
    }

    companion object {
        const val PREFERENCE_DATA_STORE_NAME = "carssok_preferences.pb"
        private val KEY_USER_NAME = "key_user_name".toKey()
        private val KEY_USER_TOKEN = "key_user_token".toKey()
        private val KEY_USER_AGREEMENT = "key_user_agreement".toBooleanKey()

        private fun String.toKey(): Preferences.Key<String> = stringPreferencesKey(this)
        private fun String.toBooleanKey(): Preferences.Key<Boolean> = booleanPreferencesKey(this)
    }
}