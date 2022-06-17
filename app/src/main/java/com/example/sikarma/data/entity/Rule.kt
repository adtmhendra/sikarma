package com.example.sikarma.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_rule")
data class Rule(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_rule")
    @NonNull
    val id_rule: Int = 0,
    @ColumnInfo(name = "id_type")
    @NonNull
    val id_type: String,
    @ColumnInfo(name = "id_symptoms")
    @NonNull
    val id_symptoms: String,
    @ColumnInfo(name = "rule_code")
    @NonNull
    val rule_code: String,
    @ColumnInfo(name = "description")
    val description: String,
)