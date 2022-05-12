package com.example.sikarma.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "symptoms")
data class Symptoms(

    @ColumnInfo(name = "id_symptoms")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id_symptoms: Int = 0,
    @ColumnInfo(name = "symptoms")
    @NonNull
    val symptoms: String,
)