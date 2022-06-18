package com.example.sikarma.data.dao

import androidx.room.*
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.relations.SymptomsWithRule
import com.example.sikarma.data.entity.relations.TypeAndRule
import kotlinx.coroutines.flow.Flow

@Dao
interface RuleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRule(rule: Rule)

    @Update
    suspend fun updateRule(rule: Rule)

    @Delete
    suspend fun deleteRule(rule: Rule)

    @Query("SELECT * FROM tb_rule ORDER BY rule_code ASC")
    fun getRules(): Flow<List<Rule>>

    @Transaction
    @Query("SELECT * FROM tb_type")
    fun getTypesAndRules(): Flow<List<TypeAndRule>>

    @Transaction
    @Query("SELECT * FROM tb_symptoms")
    fun getRuleWithSymptoms(): Flow<List<SymptomsWithRule>>

    @Query("SELECT EXISTS(SELECT * FROM tb_rule WHERE rule_code = :ruleCode OR id_type =:typeId)")
    fun getRuleName(ruleCode: String, typeId: String): Boolean

    @Query("SELECT type_description FROM tb_type WHERE type_name = :typeName")
    fun getTypeDescription(typeName: String): Flow<String>
}