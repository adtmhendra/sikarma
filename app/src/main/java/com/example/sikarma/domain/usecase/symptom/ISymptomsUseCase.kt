package com.example.sikarma.domain.usecase.symptom

import com.example.sikarma.data.entity.Symptoms
import kotlinx.coroutines.flow.Flow

interface ISymptomsUseCase {
    suspend fun insert(symptoms: Symptoms)
    suspend fun delete(symptoms: Symptoms)
    suspend fun update(symptoms: Symptoms)
    fun getSymptoms(): Flow<List<Symptoms>>
    fun getSymptomsName(symptomsCode: String, symptomsName: String): Boolean
    fun getSymptomsId(symptomsId: Int): Flow<Symptoms>
}