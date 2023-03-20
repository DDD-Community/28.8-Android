package com.ddd.carssok.core.data.repository.agreement

import com.ddd.carssok.core.data.R
import com.ddd.carssok.core.data.model.UserAgreement
import com.ddd.carssok.datastore.CarssokDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserAgreementRepository {
    fun getChecklist(): List<UserAgreement>
    fun checkedUserAgreement(): Flow<Boolean>
    suspend fun save()
}


class UserAgreementRepositoryImpl @Inject constructor(
    private val dataStore: CarssokDataStore
) : UserAgreementRepository {
    override fun getChecklist(): List<UserAgreement> {
        return listOf(
            UserAgreement(1, R.string.agreement_message1, false, R.string.agreement_message1_link),
            UserAgreement(2, R.string.agreement_message2, false, R.string.agreement_message2_link),
            UserAgreement(3, R.string.agreement_message3, false, R.string.agreement_message3_link)
        )
    }

    override fun checkedUserAgreement(): Flow<Boolean> {
        return dataStore.checkedUserAgreement()
    }

    override suspend fun save() {
        dataStore.saveUserAgreement()
    }
}