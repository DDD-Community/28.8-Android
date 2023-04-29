package com.ddd.carssok.core.network.di

import com.ddd.carssok.core.network.api.AuthApi
import com.ddd.carssok.core.network.api.CarApi
import com.ddd.carssok.core.network.api.RecordApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Singleton
    @Provides
    fun provideRecordApi(retrofit: Retrofit): RecordApi = retrofit.create(RecordApi::class.java)

    @Singleton
    @Provides
    fun provideCarApi(retrofit: Retrofit): CarApi = retrofit.create(CarApi::class.java)
}