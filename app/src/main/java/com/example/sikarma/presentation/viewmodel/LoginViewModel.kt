package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikarma.data.entity.Admin
import com.example.sikarma.domain.usecase.admin.AdminUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AdminUseCaseImpl) : ViewModel() {

    private fun insertAdmin(admin: Admin) =
        viewModelScope.launch { useCase.insert(admin = admin) }

    private fun getLoginData(username: String, password: String) =
        useCase.readAdminLoginData(adminUsername = username, adminPassword = password)

    private fun getNewAdminEntry(username: String, password: String) =
        Admin(username = username, password = password)

    fun addNewAdmin(admin: String, password: String) {
        val newAdmin = getNewAdminEntry(admin, password)
        insertAdmin(newAdmin)
    }

    fun getAdminUsername(username: String) =
        useCase.getAdminUsername(username)

    fun checkAdminDataAccount(username: String, password: String) =
        getLoginData(username, password)

    fun isNotBlank(username: String, password: String) =
        !(username.isBlank() || password.isBlank())
}