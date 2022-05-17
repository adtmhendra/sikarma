package com.example.sikarma.data.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.repository.ISymptomsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SymptomsRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    ISymptomsRepository {

    override suspend fun insert(symptoms: Symptoms) =
        appDatabase.symptomsDao.insert(symptoms = symptoms)

    override suspend fun delete(symptoms: Symptoms) =
        appDatabase.symptomsDao.delete(symptoms = symptoms)

    override suspend fun update(symptoms: Symptoms) =
        appDatabase.symptomsDao.update(symptoms = symptoms)

    override fun getSymptoms(): Flow<List<Symptoms>> =
        appDatabase.symptomsDao.getSymptoms()

    override fun getSymptomsName(symptomsCode: String, symptomsName: String): Boolean =
        appDatabase.symptomsDao.getSymptomsName(symptomsCode, symptomsName)

    override fun getSymptomsId(symptomsId: Int): Flow<Symptoms> =
        appDatabase.symptomsDao.getSymptomsId(symptomsId)
}