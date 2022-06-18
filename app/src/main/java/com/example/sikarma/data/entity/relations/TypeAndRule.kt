package com.example.sikarma.data.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.Type

data class TypeAndRule(
    @Embedded
    val type: Type? = null,
    @Relation(parentColumn = "id_type", entityColumn = "id_type")
    val rule: Rule? = null,
)