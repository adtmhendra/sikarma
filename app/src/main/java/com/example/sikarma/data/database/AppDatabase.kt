package com.example.sikarma.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sikarma.data.dao.AdminDao
import com.example.sikarma.data.dao.SymptomsDao
import com.example.sikarma.data.entity.Admin
import com.example.sikarma.data.entity.Symptoms

@Database(entities = [Admin::class, Symptoms::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val adminDao: AdminDao
    abstract val symptomsDao: SymptomsDao
}