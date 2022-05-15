package com.example.sikarma.di

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.repository.TypeRepositoryImpl
import com.example.sikarma.domain.repository.ITypeRepository
import com.example.sikarma.domain.usecase.type.TypeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TypeModule {

    @Provides
    @Singleton
    fun provideTypeRepository(appDatabase: AppDatabase): ITypeRepository =
        TypeRepositoryImpl(appDatabase)

    @Provides
    @Singleton
    fun provideTypeUseCase(iTypeRepository: ITypeRepository): TypeUseCaseImpl =
        TypeUseCaseImpl(iTypeRepository)
}