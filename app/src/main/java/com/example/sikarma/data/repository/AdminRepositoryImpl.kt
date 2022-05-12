package com.example.sikarma.data.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Admin
import com.example.sikarma.domain.repository.IAdminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    IAdminRepository {

    override fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin> =
        appDatabase.adminDao.readAdminLoginData(
            adminUsername = adminUsername,
            adminPassword = adminPassword
        )
}