package com.example.sikarma.di

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.repository.RuleRepositoryImpl
import com.example.sikarma.domain.repository.IRuleRepository
import com.example.sikarma.domain.usecase.rule.RuleUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RuleModule {

    @Provides
    @Singleton
    fun provideRuleRepository(appDatabase: AppDatabase): IRuleRepository =
        RuleRepositoryImpl(appDatabase)

    @Provides
    @Singleton
    fun provideRuleUseCase(iRuleRepository: IRuleRepository): RuleUseCaseImpl =
        RuleUseCaseImpl(iRuleRepository)
}