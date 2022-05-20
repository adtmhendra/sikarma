package com.example.sikarma.domain.repository

import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.TypeWithSymptoms
import kotlinx.coroutines.flow.Flow

interface IRuleRepository {
    suspend fun insertRule(rule: Rule)
    suspend fun updateRule(rule: Rule)
    suspend fun deleteRule(rule: Rule)
    fun getTypeWithSymptoms(): Flow<List<TypeWithSymptoms>>
//    fun getRuleAndType(): Flow<List<RuleAndType>>
}