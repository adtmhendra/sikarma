package com.example.sikarma.data.repository

import com.example.sikarma.data.database.AppDatabase
import com.example.sikarma.data.entity.Type
import com.example.sikarma.domain.repository.ITypeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TypeRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    ITypeRepository {

    override suspend fun insert(type: Type) =
        appDatabase.typeDao.insert(type = type)

    override suspend fun update(type: Type) =
        appDatabase.typeDao.update(type = type)

    override suspend fun delete(type: Type) =
        appDatabase.typeDao.delete(type = type)

    override fun getTypes(): Flow<List<Type>> =
        appDatabase.typeDao.getTypes()

    override fun getTypeName(typeCode: String, typeName: String) =
        appDatabase.typeDao.getTypeName(typeCode, typeName)

    override fun getTypeId(typeId: Int): Flow<Type> =
        appDatabase.typeDao.getTypeId(typeId)
}