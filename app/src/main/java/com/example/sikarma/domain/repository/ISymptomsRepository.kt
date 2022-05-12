package com.example.sikarma.domain.repository

import com.example.sikarma.data.entity.Symptoms

interface ISymptomsRepository {
    suspend fun insert(symptoms: Symptoms)
}