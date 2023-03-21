package com.ddd.carssok.core.data.repository.account

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.ddd.carssok.IoDispatcher
import com.ddd.carssok.core.data.Resource
import com.ddd.carssok.core.data.model.UserToken
import com.ddd.carssok.core.network.ApiSuccess
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
    suspend fun updateRemoteUserToken(): Resource<Unit>
    suspend fun checkedCarssokuser(): Resource<Boolean>
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
        when (val result = authApi.requestAuth(AuthRequestBody(localDeviceId))) {
            is ApiSuccess -> {
                dataStore.updateUserToken(result.data.model.userToken.toString())
                Resource.Success(Unit)
            }
            else -> Resource.Error()
        }
    }

    @SuppressLint("HardwareIds")
    override suspend fun checkedCarssokuser(): Resource<Boolean> = withContext(ioDispatcher) {
        val localDeviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        when (val result = authApi.getUserToken(localDeviceId)) {
            is ApiSuccess -> Resource.Success(result.data.model.userToken.isNullOrEmpty().not())
            else -> Resource.Error()
        }
    }
}