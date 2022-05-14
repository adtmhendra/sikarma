package com.example.sikarma.domain.usecase.admin

import com.example.sikarma.data.entity.Admin
import com.example.sikarma.domain.repository.IAdminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdminUseCaseImpl @Inject constructor(private val iAdminRepository: IAdminRepository) :
    IAdminUseCase {

    override fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin> =
        iAdminRepository.readAdminLoginData(
            adminUsername = adminUsername,
            adminPassword = adminPassword
        )
}