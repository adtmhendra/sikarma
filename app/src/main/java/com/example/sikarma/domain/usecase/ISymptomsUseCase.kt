package com.example.sikarma.domain.usecase

import com.example.sikarma.data.entity.Symptoms

interface ISymptomsUseCase {
    suspend fun insert(symptoms: Symptoms)
}