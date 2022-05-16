package com.example.sikarma.domain.usecase.admin

import com.example.sikarma.data.entity.Admin
import com.example.sikarma.domain.repository.IAdminRepository
import javax.inject.Inject

class AdminUseCaseImpl @Inject constructor(private val iAdminRepository: IAdminRepository) :
    IAdminUseCase {
    override suspend fun insert(admin: Admin) =
        iAdminRepository.insert(admin = admin)

    override fun readAdminLoginData(adminUsername: String, adminPassword: String) =
        iAdminRepository.readAdminLoginData(
            adminUsername = adminUsername,
            adminPassword = adminPassword
        )

    override fun getAdminUsername(adminUsername: String) =
        iAdminRepository.getAdminUsername(adminUsername)
}