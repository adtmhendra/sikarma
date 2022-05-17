package com.example.sikarma.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_admin")
data class Admin(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "admin_id")
    val id: Int = 0,
    @ColumnInfo(name = "admin_username")
    @NonNull
    val username: String,
    @ColumnInfo(name = "admin_password")
    @NonNull
    val password: String,
)
