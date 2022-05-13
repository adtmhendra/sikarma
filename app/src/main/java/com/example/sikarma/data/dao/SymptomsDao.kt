package com.example.sikarma.data.dao

import androidx.room.*
import com.example.sikarma.data.entity.Symptoms
import kotlinx.coroutines.flow.Flow

@Dao
interface SymptomsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(symptoms: Symptoms)

    @Update
    suspend fun update(symptoms: Symptoms)

    @Delete
    suspend fun delete(symptoms: Symptoms)

    @Query("SELECT * FROM tb_symptoms ORDER BY symptoms_name ASC")
    fun getSymptoms(): Flow<List<Symptoms>>

    @Query("SELECT EXISTS(SELECT * FROM tb_symptoms WHERE symptoms_name = :symptomsName)")
    fun getSymptomsName(symptomsName: String): Boolean
}