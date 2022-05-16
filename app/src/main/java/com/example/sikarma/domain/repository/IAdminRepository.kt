package com.example.sikarma.domain.repository

import com.example.sikarma.data.entity.Admin
import kotlinx.coroutines.flow.Flow

interface IAdminRepository {
    suspend fun insert(admin: Admin)
    fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin>
    fun getAdminUsername(adminUsername: String): Boolean
}