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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AccountRepository {
    suspend fun getDeviceUserToken(): UserToken?
    suspend fun updateRemoteUserToken()
    suspend fun checkedCarssokuser(): Boolean
}

class AccountRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    @ApplicationContext private val context: Context,
    private val dataStore: CarssokDataStore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AccountRepository {

    override suspend fun getDeviceUserToken(): UserToken? {
        val token = dataStore.getUserToken().firstOrNull() ?: return null
        return UserToken(token)
    }

    @SuppressLint("HardwareIds")
    override suspend fun updateRemoteUserToken() = withContext(ioDispatcher) {
        val localDeviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val result = authApi.requestAuth(AuthRequestBody(localDeviceId))

        dataStore.updateUserName(result.userToken.orEmpty())
    }

    override suspend fun checkedCarssokuser(): Boolean = withContext(ioDispatcher) {
        //임시 run catch(공통 에러처리 만들기 전까지)
        val token = kotlin.runCatching {
            authApi.getUserToken().userToken
        }.getOrNull()

        token.isNullOrEmpty().not()
    }
}