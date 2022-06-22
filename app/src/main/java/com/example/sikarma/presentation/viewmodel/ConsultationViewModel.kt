package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sikarma.domain.usecase.rule.RuleUseCaseImpl
import com.example.sikarma.domain.usecase.symptom.SymptomsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(
    symptomUseCase: SymptomsUseCaseImpl,
    ruleUseCase: RuleUseCaseImpl,
) : ViewModel() {

    val getSymptoms = symptomUseCase.getSymptoms().asLiveData()

    val getRules = ruleUseCase.getRules().asLiveData()
}