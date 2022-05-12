package com.example.sikarma.domain.usecase

import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.repository.ISymptomsRepository
import javax.inject.Inject

class SymptomsUseCaseImpl @Inject constructor(private val iSymptomsRepository: ISymptomsRepository) :
    ISymptomsUseCase {

    override suspend fun insert(symptoms: Symptoms) =
        iSymptomsRepository.insert(symptoms = symptoms)
}