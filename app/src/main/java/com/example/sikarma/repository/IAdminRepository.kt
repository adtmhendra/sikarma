package com.example.sikarma.repository

import com.example.sikarma.data.entity.Admin
import kotlinx.coroutines.flow.Flow

interface IAdminRepository {
    fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin>
}