package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.usecase.SymptomsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SymptomsViewModel @Inject constructor(private val useCase: SymptomsUseCaseImpl) :
    ViewModel() {

    private fun insertSymptoms(symptoms: Symptoms) =
        viewModelScope.launch { useCase.insert(symptoms = symptoms) }

    private fun getNewSymptomsEntry(symptoms: String) =
        Symptoms(symptoms = symptoms)

    fun addNewSymptoms(symptoms: String) {
        val newSymptoms = getNewSymptomsEntry(symptoms = symptoms)
        insertSymptoms(newSymptoms)
    }

    fun isNotBlank(symptoms: String) =
        symptoms.isNotBlank()
}