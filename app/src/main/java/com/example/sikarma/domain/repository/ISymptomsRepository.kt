package com.example.sikarma.domain.repository

import com.example.sikarma.data.entity.Symptoms
import kotlinx.coroutines.flow.Flow

interface ISymptomsRepository {
    suspend fun insert(symptoms: Symptoms)
    fun getSymptoms(): Flow<List<Symptoms>>
    fun getSymptomsName(symptomsName: String): Boolean
}