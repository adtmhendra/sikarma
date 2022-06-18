package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.LiveData
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

    val getListRuleData = useCase.getRules().asLiveData()

    val getTypesAndRules = useCase.getTypesAndRules().asLiveData()

    val getRuleWithSymptoms = useCase.getRuleWithSymptoms().asLiveData()

    fun retrieveRule(id: Int): LiveData<Rule> = useCase.getRuleId(id).asLiveData()

    private fun insertRule(rule: Rule) =
        viewModelScope.launch { useCase.insertRule(rule) }

    private fun updateRule(rule: Rule) =
        viewModelScope.launch { useCase.updateRule(rule) }

    fun deleteRule(rule: Rule) =
        viewModelScope.launch { useCase.deleteRule(rule) }

    private fun getNewRuleEntry(
        idType: String,
        idSymptoms: String,
        ruleCode: String,
        description: String,
    ) =
        Rule(
            id_type = idType,
            id_symptoms = idSymptoms,
            rule_code = ruleCode,
            description = description
        )

    private fun getUpdatedRuleEntry(
        id: Int,
        idType: String,
        idSymptoms: String,
        ruleCode: String,
        description: String,
    ) =
        Rule(
            id_rule = id,
            id_type = idType,
            id_symptoms = idSymptoms,
            rule_code = ruleCode,
            description = description,
        )

    private fun getRuleName(ruleCode: String, typeId: String) =
        useCase.getRuleName(ruleCode, typeId)

    fun addNewRule(idType: String, idSymptoms: String, ruleCode: String, description: String) {
        val newRule = getNewRuleEntry(
            idType = idType,
            idSymptoms = idSymptoms,
            description = description,
            ruleCode = ruleCode
        )
        insertRule(newRule)
    }

    fun updateRule(
        id: Int,
        idType: String,
        idSymptoms: String,
        description: String,
        ruleCode: String,
    ) {
        val updateRule = getUpdatedRuleEntry(
            id = id,
            idType = idType,
            idSymptoms = idSymptoms,
            ruleCode = ruleCode,
            description = description
        )
        updateRule(updateRule)
    }

    fun getTypeDescription(typeName: String) = useCase.getTypeDescription(typeName)

    fun checkData(ruleCode: String, typeId: String) = getRuleName(ruleCode, typeId)
}