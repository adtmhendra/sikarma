package com.example.sikarma.domain.repository

import com.example.sikarma.data.entity.Type
import kotlinx.coroutines.flow.Flow

interface ITypeRepository {
    suspend fun insert(type: Type)
    suspend fun update(type: Type)
    suspend fun delete(type: Type)
    fun getTypes(): Flow<List<Type>>
    fun getTypeName(typeCode: String, typeName: String): Boolean
    fun getTypeId(typeId: Int): Flow<Type>
}