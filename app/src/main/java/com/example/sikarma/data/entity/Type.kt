package com.example.sikarma.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_type")
data class Type(
    @ColumnInfo(name = "id_type")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id_type: Int = 0,
    @ColumnInfo(name = "type_code")
    @NonNull
    val type_code: String,
    @ColumnInfo(name = "type_name")
    @NonNull
    val type_name: String,
    @ColumnInfo(name = "type_description")
    val type_desc: String,
    @ColumnInfo(name = "type_solution")
    val type_solution: String,
)