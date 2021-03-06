package com.example.sikarma.domain.usecase.rule

import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.relations.SymptomsWithRule
import com.example.sikarma.data.entity.relations.TypeAndRule
import kotlinx.coroutines.flow.Flow

interface IRuleUseCase {
    suspend fun insertRule(rule: Rule)
    suspend fun updateRule(rule: Rule)
    suspend fun deleteRule(rule: Rule)
    fun getTypesAndRules(): Flow<List<TypeAndRule>>
    fun getRuleWithSymptoms(): Flow<List<SymptomsWithRule>>
    fun getRuleName(ruleCode: String, typeId: String): Boolean
    fun getTypeDescription(typeName: String): Flow<String>
    fun getRules(): Flow<List<Rule>>
    fun getRuleId(ruleId: Int): Flow<Rule>
}