package com.example.sikarma.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sikarma.data.entity.Admin
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(admin: Admin)

    @Query("SELECT * FROM tb_admin WHERE admin_username LIKE :adminUsername AND admin_password LIKE :adminPassword")
    fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin>

    @Query("SELECT EXISTS(SELECT * FROM tb_admin WHERE admin_username = :adminUsername)")
    fun getAdminUsername(adminUsername: String): Boolean
}