package com.ddd.carssok.core.data.di

import com.ddd.carssok.core.data.repository.record.drive.RecordDriveRepository
import com.ddd.carssok.core.data.repository.record.drive.RecordDriveRepositoryImpl
import com.ddd.carssok.core.data.source.record.drive.RecordDriveLocalDataSource
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

    @Module
    @InstallIn(ViewModelComponent::class)
    interface RecordBindsModule {

        @Binds
        @ViewModelScoped
        fun bindRecordDriveRepository(
            repository: RecordDriveRepositoryImpl
        ): RecordDriveRepository

    }
}