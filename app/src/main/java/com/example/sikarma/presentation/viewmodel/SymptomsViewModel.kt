package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.usecase.SymptomsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SymptomsViewModel @Inject constructor(private val useCase: SymptomsUseCaseImpl) :
    ViewModel() {

    val getListSymptomsData: LiveData<List<Symptoms>> = useCase.getSymptoms().asLiveData()

    private fun insertSymptoms(symptoms: Symptoms) =
        viewModelScope.launch { useCase.insert(symptoms = symptoms) }

    fun deleteSymptoms(symptoms: Symptoms) =
        viewModelScope.launch { useCase.delete(symptoms = symptoms) }

    private fun getSymptomsName(symptoms: String) =
        useCase.getSymptomsName(symptomsName = symptoms)

    private fun getNewSymptomsEntry(symptoms: String) =
        Symptoms(symptoms = symptoms)

    fun addNewSymptoms(symptoms: String) {
        val newSymptoms = getNewSymptomsEntry(symptoms)
        insertSymptoms(newSymptoms)
    }

    fun checkData(newSymptoms: String) =
        getSymptomsName(newSymptoms)
}