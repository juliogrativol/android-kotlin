package com.example.android.myaddressbook

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Contact::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}