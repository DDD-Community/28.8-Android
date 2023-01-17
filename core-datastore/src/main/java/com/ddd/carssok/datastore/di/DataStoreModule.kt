package com.ddd.carssok.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.ddd.carssok.datastore.CarssokDataStore
import com.ddd.carssok.datastore.CarssokDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideCarssokPreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(CarssokDataStoreImpl.PREFERENCE_DATA_STORE_NAME)
        }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindsDataStore {
        @Binds
        abstract fun bindDataStore(carssokDataStoreImpl: CarssokDataStoreImpl): CarssokDataStore
    }
}