package com.example.sikarma.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.sikarma.data.entity.Admin
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminDao {

    @Query("SELECT * FROM tb_admin WHERE admin_username LIKE :adminUsername AND admin_password LIKE :adminPassword")
    fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin>
}