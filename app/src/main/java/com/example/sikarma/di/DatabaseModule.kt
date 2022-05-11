package com.example.sikarma.di

import android.content.Context
import androidx.room.Room
import com.example.sikarma.data.dao.AdminDao
import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.repository.AdminRepositoryImpl
import com.example.sikarma.repository.IAdminRepository
import com.example.sikarma.usecase.AdminUseCaseImpl
import com.example.sikarma.usecase.IAdminUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "item_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): AdminDao {
        return appDatabase.adminDao
    }

    @Provides
    @Singleton
    fun adminRepository(appDatabase: AppDatabase): IAdminRepository {
        return AdminRepositoryImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideUseCase(iAdminRepository: IAdminRepository): IAdminUseCase {
        return AdminUseCaseImpl(iAdminRepository)
    }
}