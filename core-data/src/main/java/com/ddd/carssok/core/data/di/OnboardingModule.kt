package com.ddd.carssok.core.data.di

import com.ddd.carssok.core.data.repository.onboarding.FakeOnBoardingRepositoryImpl
import com.ddd.carssok.core.data.repository.onboarding.OnBoardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class OnBoardingModule {

    @Module
    @InstallIn(ViewModelComponent::class)
    interface OnBoardingBindsModule {

        @Binds
        @ViewModelScoped
        fun bindOnBoardingRepository(
            repository: FakeOnBoardingRepositoryImpl
        ): OnBoardingRepository
    }
}