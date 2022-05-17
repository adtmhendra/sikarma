package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.domain.usecase.symptom.SymptomsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SymptomsViewModel @Inject constructor(private val useCase: SymptomsUseCaseImpl) :
    ViewModel() {

    val getListSymptomsData: LiveData<List<Symptoms>> = useCase.getSymptoms().asLiveData()

    fun retrieveSymptoms(id: Int): LiveData<Symptoms> = useCase.getSymptomsId(id).asLiveData()

    private fun insertSymptoms(symptoms: Symptoms) =
        viewModelScope.launch { useCase.insert(symptoms = symptoms) }

    private fun updateSymptoms(symptoms: Symptoms) =
        viewModelScope.launch { useCase.update(symptoms = symptoms) }

    fun deleteSymptoms(symptoms: Symptoms) =
        viewModelScope.launch { useCase.delete(symptoms = symptoms) }

    private fun getSymptomsName(symptomsCode: String, symptomsName: String) =
        useCase.getSymptomsName(symptomsCode = symptomsCode, symptomsName = symptomsName)

    private fun getNewSymptomsEntry(symptomsCode: String, symptomsName: String) =
        Symptoms(symptoms_code = symptomsCode, symptoms_name = symptomsName)

    private fun getUpdatedSymptomsEntry(id: Int, symptomsCode: String, symptomsName: String) =
        Symptoms(id_symptoms = id, symptoms_code = symptomsCode, symptoms_name = symptomsName)

    fun addNewSymptoms(symptomsCode: String, symptomsName: String) {
        val newSymptoms = getNewSymptomsEntry(symptomsCode, symptomsName)
        insertSymptoms(newSymptoms)
    }

    fun updateSymptoms(id: Int, symptomsCode: String, symptomsName: String) {
        val updateSymptoms = getUpdatedSymptomsEntry(id, symptomsCode, symptomsName)
        updateSymptoms(updateSymptoms)
    }

    fun checkData(symptomsCode: String, symptomsName: String) =
        getSymptomsName(symptomsCode, symptomsName)
}