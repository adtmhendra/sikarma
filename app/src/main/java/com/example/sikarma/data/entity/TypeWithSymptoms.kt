package com.example.sikarma.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TypeWithSymptoms(
    @Embedded val type: Type,
    @Relation(
        parentColumn = "id_type",
        entityColumn = "id_symptoms",
        associateBy = Junction(TypeSymptomCrossRef::class))
    val symptoms: List<Symptoms>,
)