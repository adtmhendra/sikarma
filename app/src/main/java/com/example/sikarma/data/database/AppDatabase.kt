package com.example.sikarma.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sikarma.data.dao.AdminDao
import com.example.sikarma.data.dao.RuleDao
import com.example.sikarma.data.dao.SymptomsDao
import com.example.sikarma.data.dao.TypeDao
import com.example.sikarma.data.entity.Admin
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.data.entity.Type

@Database(entities = [Admin::class,
    Symptoms::class,
    Type::class,
    Rule::class],
    version = 2,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val adminDao: AdminDao
    abstract val symptomsDao: SymptomsDao
    abstract val typeDao: TypeDao
    abstract val ruleDao: RuleDao
}