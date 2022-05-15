package com.example.sikarma.domain.usecase.type

import com.example.sikarma.data.entity.Type
import kotlinx.coroutines.flow.Flow

interface ITypeUseCase {
    suspend fun insert(type: Type)
    suspend fun update(type: Type)
    suspend fun delete(type: Type)
    fun getTypes(): Flow<List<Type>>
    fun getTypeName(typeCode: String, typeName: String): Boolean
    fun getTypeId(typeId: Int): Flow<Type>
}