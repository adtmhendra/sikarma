package com.example.sikarma.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sikarma.data.dao.AdminDao
import com.example.sikarma.data.entity.Admin

@Database(entities = [Admin::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val adminDao: AdminDao
}