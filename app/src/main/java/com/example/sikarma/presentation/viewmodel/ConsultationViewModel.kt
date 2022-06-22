package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sikarma.domain.usecase.rule.RuleUseCaseImpl
import com.example.sikarma.domain.usecase.symptom.SymptomsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(
    private val symptomUseCase: SymptomsUseCaseImpl,
    private val ruleUseCase: RuleUseCaseImpl,
) : ViewModel() {

    val getListSymptomData = symptomUseCase.getSymptoms().asLiveData()

    val getListRuleData = ruleUseCase.getRules().asLiveData()
}