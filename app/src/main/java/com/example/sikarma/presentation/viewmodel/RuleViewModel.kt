package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.domain.usecase.rule.RuleUseCaseImpl
import com.example.sikarma.domain.usecase.symptom.SymptomsUseCaseImpl
import com.example.sikarma.domain.usecase.type.TypeUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RuleViewModel @Inject constructor(
    private val useCase: RuleUseCaseImpl,
    private val typeUseCase: TypeUseCaseImpl,
    private val symptomUseCase: SymptomsUseCaseImpl,
) : ViewModel() {

    private fun insertRule(rule: Rule) =
        viewModelScope.launch { useCase.insertRule(rule) }

    private fun updateRule(rule: Rule) =
        viewModelScope.launch { useCase.updateRule(rule) }

    private fun deleteRule(rule: Rule) =
        viewModelScope.launch { useCase.deleteRule(rule) }

    val getTypes = typeUseCase.getTypes().asLiveData()

    val getSymptoms = symptomUseCase.getSymptoms().asLiveData()

    val getTypeWithSymptoms = useCase.getTypeWithSymptoms().asLiveData()

//    fun getRuleAndType() = useCase.getRuleAndType().asLiveData()
}