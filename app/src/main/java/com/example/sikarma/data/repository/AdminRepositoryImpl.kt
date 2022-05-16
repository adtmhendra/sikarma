package com.example.sikarma.data.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Admin
import com.example.sikarma.domain.repository.IAdminRepository
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    IAdminRepository {

    override suspend fun insert(admin: Admin) =
        appDatabase.adminDao.insert(admin = admin)

    override fun readAdminLoginData(adminUsername: String, adminPassword: String) =
        appDatabase.adminDao.readAdminLoginData(
            adminUsername = adminUsername,
            adminPassword = adminPassword
        )

    override fun getAdminUsername(adminUsername: String) =
        appDatabase.adminDao.getAdminUsername(adminUsername)
}