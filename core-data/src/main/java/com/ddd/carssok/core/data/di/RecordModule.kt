package com.ddd.carssok.core.data.di

import com.ddd.carssok.core.data.repository.onboarding.drive.RecordDriveLocalDataSource
import com.ddd.carssok.core.data.repository.record.accident.RecordAccidentRepository
import com.ddd.carssok.core.data.repository.record.accident.RecordAccidentRepositoryImpl
import com.ddd.carssok.core.data.repository.record.drive.RecordDriveRepository
import com.ddd.carssok.core.data.repository.record.drive.RecordDriveRepositoryImpl
import com.ddd.carssok.core.data.repository.record.refuel.RecordRefuelRepository
import com.ddd.carssok.core.data.repository.record.refuel.RecordRefuelRepositoryImpl
import com.ddd.carssok.core.data.source.record.refuel.RecordRefuelLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(
    includes = [
        RecordModule.RecordBindsModule::class
    ]
)
@InstallIn(ViewModelComponent::class)
class RecordModule {

    @Provides
    @ViewModelScoped
    fun provideRecordDriveLocalDataSource(): RecordDriveLocalDataSource = RecordDriveLocalDataSource()

    @Provides
    @ViewModelScoped
    fun provideRecordRefuelLocalDataSource(): RecordRefuelLocalDataSource = RecordRefuelLocalDataSource()

    @Module
    @InstallIn(ViewModelComponent::class)
    interface RecordBindsModule {

        @Binds
        @ViewModelScoped
        fun bindRecordDriveRepository(
            repository: RecordDriveRepositoryImpl
        ): RecordDriveRepository

        @Binds
        @ViewModelScoped
        fun bindRecordRefuelRepository(
            repository: RecordRefuelRepositoryImpl
        ): RecordRefuelRepository

        @Binds
        @ViewModelScoped
        fun bindRecordAccidentRepository(
            repository: RecordAccidentRepositoryImpl
        ): RecordAccidentRepository
    }
}