package com.ddd.carssok.datastore

import kotlinx.coroutines.flow.Flow


interface CarssokDataStore {

    /** 샘플 코드 */
    fun getUserName() : Flow<String>

    suspend fun updateUserName(name: String)


    fun clear()

}