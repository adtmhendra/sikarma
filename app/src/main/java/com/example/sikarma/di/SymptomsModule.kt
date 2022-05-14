package com.example.sikarma.di

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.repository.SymptomsRepositoryImpl
import com.example.sikarma.domain.repository.ISymptomsRepository
import com.example.sikarma.domain.usecase.symptom.SymptomsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SymptomsModule {

    @Provides
    @Singleton
    fun provideSymptomsRepository(appDatabase: AppDatabase): ISymptomsRepository =
        SymptomsRepositoryImpl(appDatabase)

    @Provides
    @Singleton
    fun provideSymptomsUseCase(iSymptomsRepository: ISymptomsRepository): SymptomsUseCaseImpl =
        SymptomsUseCaseImpl(iSymptomsRepository)
}