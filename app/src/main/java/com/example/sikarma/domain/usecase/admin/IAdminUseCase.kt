package com.example.sikarma.domain.usecase.admin

import com.example.sikarma.data.entity.Admin
import kotlinx.coroutines.flow.Flow

interface IAdminUseCase {
    suspend fun insert(admin: Admin)
    fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin>
    fun getAdminUsername(adminUsername: String): Boolean
}