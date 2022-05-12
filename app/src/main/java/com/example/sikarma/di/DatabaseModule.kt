package com.example.sikarma.di

import android.content.Context
import androidx.room.Room
import com.example.sikarma.data.database.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java,
            "item_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}