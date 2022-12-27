package com.ddd.carssok.core.data.di

import com.ddd.carssok.core.data.repository.onboarding.FakeOnboardingRepositoryImpl
import com.ddd.carssok.core.data.repository.onboarding.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class OnboardingModule {

    @Module
    @InstallIn(ViewModelComponent::class)
    interface OnboardingBindsModule {

        @Binds
        @ViewModelScoped
        fun bindOnboardingRepository(
            repository: FakeOnboardingRepositoryImpl
        ): OnboardingRepository
    }
}