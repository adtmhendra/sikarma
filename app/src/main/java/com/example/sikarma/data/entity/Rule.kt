package com.example.sikarma.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_rule")
data class Rule(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_rule")
    val id_rule: Int = 0,
    @ColumnInfo(name = "rule_code")
    @NonNull
    val rule_code: String,
    @ColumnInfo(name = "type_code")
    @NonNull
    val type_code: String,
    @ColumnInfo(name = "symptoms_name")
    @NonNull
    val symptoms_name: String,
)