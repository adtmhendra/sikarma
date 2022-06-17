package com.example.sikarma.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SymptomsWithRule(
    @Embedded
    val symptoms: Symptoms? = null,
    @Relation(parentColumn = "id_symptoms", entityColumn = "id_symptoms")
    val rule: List<Rule>? = null,
)
