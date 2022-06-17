package com.example.sikarma.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TypeAndRule(
    @Embedded
    val type: Type? = null,
    @Relation(parentColumn = "id_type", entityColumn = "id_type")
    val rule: Rule? = null,
)