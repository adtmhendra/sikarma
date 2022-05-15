package com.example.sikarma.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sikarma.data.entity.Type
import com.example.sikarma.domain.usecase.type.TypeUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeViewModel @Inject constructor(private val useCase: TypeUseCaseImpl) : ViewModel() {

    val getListTypeData: LiveData<List<Type>> = useCase.getTypes().asLiveData()

    fun retrieveType(id: Int): LiveData<Type> = useCase.getTypeId(id).asLiveData()

    private fun insertType(type: Type) =
        viewModelScope.launch { useCase.insert(type = type) }

    private fun updateType(type: Type) =
        viewModelScope.launch { useCase.update(type = type) }

    fun deleteType(type: Type) =
        viewModelScope.launch { useCase.delete(type = type) }

    private fun getTypeName(typeCode: String, typeName: String) =
        useCase.getTypeName(typeCode = typeCode, typeName = typeName)

    private fun getNewTypeEntry(
        typeCode: String,
        typeName: String,
        typeDesc: String,
        typeSolution: String,
    ) =
        Type(type_code = typeCode,
            type_name = typeName,
            type_desc = typeDesc,
            type_solution = typeSolution)

    private fun getUpdatedTypeEntry(
        id: Int, typeCode: String,
        typeName: String,
        typeDesc: String,
        typeSolution: String,
    ) =
        Type(id_type = id,
            type_code = typeCode,
            type_name = typeName,
            type_desc = typeDesc,
            type_solution = typeSolution)

    fun addNewType(typeCode: String, typeName: String, typeDesc: String, typeSolution: String) {
        val newType = getNewTypeEntry(typeCode, typeName, typeDesc, typeSolution)
        insertType(newType)
    }

    fun updateType(
        id: Int,
        typeCode: String,
        typeName: String,
        typeDesc: String,
        typeSolution: String,
    ) {
        val updateType = getUpdatedTypeEntry(id, typeCode, typeName, typeDesc, typeSolution)
        updateType(updateType)
    }

    fun checkData(typeCode: String, typeName: String) =
        getTypeName(typeCode, typeName)
}