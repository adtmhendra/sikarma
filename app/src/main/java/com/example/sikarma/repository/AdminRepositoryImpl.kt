package com.example.sikarma.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Admin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    IAdminRepository {

    override fun readAdminLoginData(adminUsername: String, adminPassword: String): Flow<Admin> {
        return appDatabase.adminDao.readAdminLoginData(
            adminUsername = adminUsername,
            adminPassword = adminPassword
        )
    }
}