package com.ddd.carssok.core.data.repository.account

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.ddd.carssok.IoDispatcher
import com.ddd.carssok.core.data.model.UserToken
import com.ddd.carssok.core.network.api.AuthApi
import com.ddd.carssok.core.network.model.AuthRequestBody
import com.ddd.carssok.datastore.CarssokDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AccountRepository {
    suspend fun requestUser(): UserToken
}

class AccountRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    @ApplicationContext private val context: Context,
    private val dataStore: CarssokDataStore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AccountRepository {

    @SuppressLint("HardwareIds")
    override suspend fun requestUser(): UserToken = withContext(ioDispatcher) {
        val uuid = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val result = authApi.requestAuth(AuthRequestBody(uuid))
        dataStore.updateUserName(result.userToken)
        UserToken(result.userToken)
    }
}