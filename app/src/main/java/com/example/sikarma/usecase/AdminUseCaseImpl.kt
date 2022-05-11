package com.example.sikarma.usecase

import com.example.sikarma.data.entity.Admin
import com.example.sikarma.repository.IAdminRepository
import kotlinx.coroutines.flow.Flow

class AdminUseCaseImpl(private val iAdminRepository: IAdminRepository) : IAdminUseCase {

    override fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin> {
        return iAdminRepository.readAdminLoginData(
            adminUsername = adminUsername,
            adminPassword = adminPassword
        )
    }
}