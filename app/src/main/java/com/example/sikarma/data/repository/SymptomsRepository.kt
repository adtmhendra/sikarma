package com.example.sikarma.data.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.repository.ISymptomsRepository
import javax.inject.Inject

class SymptomsRepository @Inject constructor(private val appDatabase: AppDatabase) :
    ISymptomsRepository {

    override suspend fun insert(symptoms: Symptoms) =
        appDatabase.symptomsDao.insert(symptoms = symptoms)
}