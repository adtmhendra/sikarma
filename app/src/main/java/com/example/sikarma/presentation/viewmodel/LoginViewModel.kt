package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sikarma.data.entity.Admin
import com.example.sikarma.domain.usecase.AdminUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AdminUseCaseImpl) : ViewModel() {

    private fun getLoginData(username: String, password: String): Flow<Admin> =
        useCase.readAdminLoginData(adminUsername = username, adminPassword = password)

    fun checkAdminDataAccount(username: String, password: String): Flow<Admin> =
        getLoginData(username, password)

    fun isNotBlank(username: String, password: String): Boolean =
        !(username.isBlank() || password.isBlank())
}