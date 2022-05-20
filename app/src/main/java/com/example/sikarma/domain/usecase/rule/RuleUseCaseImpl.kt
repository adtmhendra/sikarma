package com.example.sikarma.domain.usecase.rule

import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.Type
import com.example.sikarma.data.entity.TypeSymptom
import com.example.sikarma.data.entity.TypeWithSymptoms
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

    override fun getTypeWithSymptoms(): Flow<List<TypeWithSymptoms>> =
        iRuleRepository.getTypeWithSymptoms()

//    override fun getRuleAndType(): Flow<List<RuleAndType>> =
//        iRuleRepository.getRuleAndType()
}