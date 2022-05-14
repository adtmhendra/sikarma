package com.example.sikarma.domain.usecase.admin

import com.example.sikarma.data.entity.Admin
import kotlinx.coroutines.flow.Flow

interface IAdminUseCase {
    fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin>
}