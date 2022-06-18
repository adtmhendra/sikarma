package com.example.sikarma.data.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.relations.SymptomsWithRule
import com.example.sikarma.data.entity.relations.TypeAndRule
import com.example.sikarma.domain.repository.IRuleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RuleRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    IRuleRepository {

    override suspend fun insertRule(rule: Rule) =
        appDatabase.ruleDao.insertRule(rule)

    override suspend fun updateRule(rule: Rule) =
        appDatabase.ruleDao.updateRule(rule)

    override suspend fun deleteRule(rule: Rule) =
        appDatabase.ruleDao.deleteRule(rule)

    override fun getTypesAndRules(): Flow<List<TypeAndRule>> =
        appDatabase.ruleDao.getTypesAndRules()

    override fun getRuleWithSymptoms(): Flow<List<SymptomsWithRule>> =
        appDatabase.ruleDao.getRuleWithSymptoms()

    override fun getRuleName(ruleCode: String, typeId: String): Boolean =
        appDatabase.ruleDao.getRuleName(ruleCode, typeId)

    override fun getTypeDescription(typeName: String): Flow<String> =
        appDatabase.ruleDao.getTypeDescription(typeName)

    override fun getRules(): Flow<List<Rule>> =
        appDatabase.ruleDao.getRules()

    override fun getRuleId(ruleId: Int): Flow<Rule> =
        appDatabase.ruleDao.getRuleId(ruleId)
}