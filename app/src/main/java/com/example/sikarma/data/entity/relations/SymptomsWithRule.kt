package com.example.sikarma.data.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.Symptoms

data class SymptomsWithRule(
    @Embedded
    val symptoms: Symptoms? = null,
    @Relation(parentColumn = "id_symptoms", entityColumn = "id_symptoms")
    val rule: List<Rule>? = null,
)
