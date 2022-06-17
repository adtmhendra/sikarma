package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.domain.usecase.rule.RuleUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RuleViewModel @Inject constructor(
    private val useCase: RuleUseCaseImpl,
) : ViewModel() {

    val getTypesAndRules = useCase.getTypesAndRules().asLiveData()

    val getRuleWithSymptoms = useCase.getRuleWithSymptoms().asLiveData()

    private fun insertRule(rule: Rule) =
        viewModelScope.launch { useCase.insertRule(rule) }

    private fun updateRule(rule: Rule) =
        viewModelScope.launch { useCase.updateRule(rule) }

    private fun deleteRule(rule: Rule) =
        viewModelScope.launch { useCase.deleteRule(rule) }

    private fun getNewRuleEntry(
        idType: String,
        idSymptoms: String,
        ruleCode: String,
        descriprion: String,
    ) =
        Rule(
            id_type = idType,
            id_symptoms = idSymptoms,
            rule_code = ruleCode,
            description = descriprion
        )

    private fun getRuleName(ruleCode: String, typeId: String) =
        useCase.getRuleName(ruleCode, typeId)

    fun addNewRule(idType: String, idSymptoms: String, ruleCode: String, descriprion: String) {
        val newRule = getNewRuleEntry(
            idType = idType,
            idSymptoms = idSymptoms,
            ruleCode = ruleCode,
            descriprion = descriprion
        )
        insertRule(newRule)
    }

    fun getTypeDescription(typeName: String) = useCase.getTypeDescription(typeName)

    fun checkData(ruleCode: String, typeId: String) = getRuleName(ruleCode, typeId)
}