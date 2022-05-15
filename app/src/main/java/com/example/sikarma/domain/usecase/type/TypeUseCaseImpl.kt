package com.example.sikarma.domain.usecase.type

import com.example.sikarma.data.entity.Type
import com.example.sikarma.domain.repository.ITypeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TypeUseCaseImpl @Inject constructor(private val iTypeRepository: ITypeRepository) :
    ITypeUseCase {

    override suspend fun insert(type: Type) =
        iTypeRepository.insert(type = type)

    override suspend fun update(type: Type) =
        iTypeRepository.update(type = type)

    override suspend fun delete(type: Type) =
        iTypeRepository.delete(type = type)

    override fun getTypes(): Flow<List<Type>> =
        iTypeRepository.getTypes()

    override fun getTypeName(typeCode: String, typeName: String) =
        iTypeRepository.getTypeName(typeCode, typeName)

    override fun getTypeId(typeId: Int): Flow<Type> =
        iTypeRepository.getTypeId(typeId)
}