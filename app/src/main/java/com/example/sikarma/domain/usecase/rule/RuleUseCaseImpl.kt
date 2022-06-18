package com.example.sikarma.domain.usecase.rule

import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.relations.SymptomsWithRule
import com.example.sikarma.data.entity.relations.TypeAndRule
import com.example.sikarma.domain.repository.IRuleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RuleUseCaseImpl @Inject constructor(private val iRuleRepository: IRuleRepository) :
    IRuleUseCase {

    override suspend fun insertRule(rule: Rule) =
        iRuleRepository.insertRule(rule)

    override suspend fun updateRule(rule: Rule) =
        iRuleRepository.updateRule(rule)

    override suspend fun deleteRule(rule: Rule) =
        iRuleRepository.deleteRule(rule)

    override fun getTypesAndRules(): Flow<List<TypeAndRule>> =
        iRuleRepository.getTypesAndRules()

    override fun getRuleWithSymptoms(): Flow<List<SymptomsWithRule>> =
        iRuleRepository.getRuleWithSymptoms()

    override fun getRuleName(ruleCode: String, typeId: String): Boolean =
        iRuleRepository.getRuleName(ruleCode, typeId)

    override fun getTypeDescription(typeName: String): Flow<String> =
        iRuleRepository.getTypeDescription(typeName)
}