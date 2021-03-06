package com.example.sikarma.domain.usecase.symptom

import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.repository.ISymptomsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SymptomsUseCaseImpl @Inject constructor(private val iSymptomsRepository: ISymptomsRepository) :
    ISymptomsUseCase {

    override suspend fun insert(symptoms: Symptoms) =
        iSymptomsRepository.insert(symptoms = symptoms)

    override suspend fun delete(symptoms: Symptoms) =
        iSymptomsRepository.delete(symptoms = symptoms)

    override suspend fun update(symptoms: Symptoms) =
        iSymptomsRepository.update(symptoms = symptoms)

    override fun getSymptoms(): Flow<List<Symptoms>> =
        iSymptomsRepository.getSymptoms()

    override fun getSymptomsName(symptomsCode: String, symptomsName: String): Boolean =
        iSymptomsRepository.getSymptomsName(symptomsCode, symptomsName)

    override fun getSymptomsId(symptomsId: Int): Flow<Symptoms> =
        iSymptomsRepository.getSymptomsId(symptomsId)
}