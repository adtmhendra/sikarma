package com.example.sikarma.data.dao

import androidx.room.*
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.TypeSymptom
import com.example.sikarma.data.entity.TypeWithSymptoms
import kotlinx.coroutines.flow.Flow

@Dao
interface RuleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRule(rule: Rule)

    @Update
    suspend fun updateRule(rule: Rule)

    @Delete
    suspend fun deleteRule(rule: Rule)

    @Transaction
    @Query("SELECT * FROM tb_type")
    fun getTypeWithSymptoms(): Flow<List<TypeWithSymptoms>>

    @Query("SELECT tb_type.type_name AS type_name, tb_symptoms.symptoms_name AS symptom_name " +
            "FROM tb_type, tb_symptoms " +
            "WHERE tb_type.id_type = tb_symptoms.id_symptoms")
    fun loadTypeAndSymptomName(): Flow<List<TypeSymptom>>
}