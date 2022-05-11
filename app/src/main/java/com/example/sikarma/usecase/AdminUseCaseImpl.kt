package com.example.sikarma.usecase

import com.example.sikarma.data.entity.Admin
import com.example.sikarma.repository.AdminRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdminUseCaseImpl @Inject constructor(private val adminRepositoryImpl: AdminRepositoryImpl) :
    IAdminUseCase {

    override fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin> =
        adminRepositoryImpl.readAdminLoginData(
            adminUsername = adminUsername,
            adminPassword = adminPassword
        )
}