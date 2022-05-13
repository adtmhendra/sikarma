package com.example.sikarma.domain.usecase

import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.repository.ISymptomsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SymptomsUseCaseImpl @Inject constructor(private val iSymptomsRepository: ISymptomsRepository) :
    ISymptomsUseCase {

    override suspend fun insert(symptoms: Symptoms) =
        iSymptomsRepository.insert(symptoms = symptoms)

    override fun getSymptoms(): Flow<List<Symptoms>> =
        iSymptomsRepository.getSymptoms()

    override fun getSymptomsName(symptomsName: String): Boolean =
        iSymptomsRepository.getSymptomsName(symptomsName = symptomsName)
}