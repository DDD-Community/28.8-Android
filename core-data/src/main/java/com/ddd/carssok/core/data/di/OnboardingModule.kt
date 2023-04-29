package com.ddd.carssok.core.data.di

import com.ddd.carssok.core.data.repository.OnBoardingRepository
import com.ddd.carssok.core.data.repository.OnBoardingRepositoryImpl
import com.ddd.carssok.core.data.repository.account.AccountRepository
import com.ddd.carssok.core.data.repository.account.AccountRepositoryImpl
import com.ddd.carssok.core.data.repository.agreement.UserAgreementRepository
import com.ddd.carssok.core.data.repository.agreement.UserAgreementRepositoryImpl
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
        fun bindAccountRepository(
            repository: AccountRepositoryImpl
        ): AccountRepository

        @Binds
        @ViewModelScoped
        fun bindUserAgreementRepository(
            repository: UserAgreementRepositoryImpl
        ): UserAgreementRepository

        @Binds
        @ViewModelScoped
        fun bindCarBrandsRepository(
            repository: OnBoardingRepositoryImpl
        ): OnBoardingRepository
    }
}