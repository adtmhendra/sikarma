package com.example.sikarma.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id_type", "id_symptoms"])
data class TypeSymptomCrossRef(
    val id_type: Int,
    val id_symptoms: Int,
)