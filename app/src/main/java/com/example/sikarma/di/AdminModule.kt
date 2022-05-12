package com.example.sikarma.di

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.repository.AdminRepositoryImpl
import com.example.sikarma.domain.repository.IAdminRepository
import com.example.sikarma.domain.usecase.AdminUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdminModule {

    @Provides
    @Singleton
    fun provideAdminRepository(appDatabase: AppDatabase): IAdminRepository =
        AdminRepositoryImpl(appDatabase)

    @Provides
    @Singleton
    fun provideAdminUseCase(iAdminRepository: IAdminRepository): AdminUseCaseImpl =
        AdminUseCaseImpl(iAdminRepository)
}