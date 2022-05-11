package com.example.sikarma.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sikarma.usecase.IAdminUseCase
import javax.inject.Inject

//class LoginViewModelFactory @Inject constructor(private val useCase: IAdminUseCase) :
//    ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        @Suppress("UNCHECKED_CAST")
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            return LoginViewModel(useCase) as T
//        }
//        throw IllegalArgumentException("Unable to Construct ViewModel")
//    }
//}