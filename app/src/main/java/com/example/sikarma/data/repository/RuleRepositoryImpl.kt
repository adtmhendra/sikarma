package com.example.sikarma.data.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.Type
import com.example.sikarma.data.entity.TypeSymptom
import com.example.sikarma.data.entity.TypeWithSymptoms
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

    override fun getTypeWithSymptoms(): Flow<List<TypeWithSymptoms>> =
        appDatabase.ruleDao.getTypeWithSymptoms()

//    override fun getRuleAndType(): Flow<List<RuleAndType>> =
//        appDatabase.ruleDao.getRuleAndType()
}