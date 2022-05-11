package com.example.sikarma.di

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.repository.AdminRepositoryImpl
import com.example.sikarma.usecase.AdminUseCaseImpl
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
    fun provideAdminRepository(appDatabase: AppDatabase): AdminRepositoryImpl =
        AdminRepositoryImpl(appDatabase)

    @Provides
    @Singleton
    fun provideAdminUseCase(adminRepositoryImpl: AdminRepositoryImpl): AdminUseCaseImpl =
        AdminUseCaseImpl(adminRepositoryImpl)
}