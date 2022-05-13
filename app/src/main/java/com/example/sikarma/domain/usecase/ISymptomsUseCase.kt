package com.example.sikarma.domain.usecase

import com.example.sikarma.data.entity.Symptoms
import kotlinx.coroutines.flow.Flow

interface ISymptomsUseCase {
    suspend fun insert(symptoms: Symptoms)
    fun getSymptoms(): Flow<List<Symptoms>>
    fun getSymptomsName(symptomsName: String): Boolean
}