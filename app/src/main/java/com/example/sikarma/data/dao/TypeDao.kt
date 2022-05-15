package com.example.sikarma.data.dao

import androidx.room.*
import com.example.sikarma.data.entity.Type
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(type: Type)

    @Update
    suspend fun update(type: Type)

    @Delete
    suspend fun delete(type: Type)

    @Query("SELECT * FROM tb_type ORDER BY type_name ASC")
    fun getTypes(): Flow<List<Type>>

    @Query("SELECT EXISTS(SELECT * FROM tb_type WHERE type_code = :typeCode OR type_name = :typeName)")
    fun getTypeName(typeCode: String, typeName: String): Boolean

    @Query("SELECT * FROM tb_type WHERE id_type = :typeId")
    fun getTypeId(typeId: Int): Flow<Type>
}