package com.example.sikarma.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_symptoms")
data class Symptoms(
    @ColumnInfo(name = "id_symptoms")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id_symptoms: Int = 0,
    @ColumnInfo(name = "symptoms_code")
    @NonNull
    val symptoms_code: String,
    @ColumnInfo(name = "symptoms_name")
    @NonNull
    val symptoms_name: String,
)