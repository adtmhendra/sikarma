package com.example.sikarma.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sikarma.data.entity.Symptoms

@Dao
interface SymptomsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(symptoms: Symptoms)
}