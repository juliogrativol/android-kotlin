package com.example.android.myaddressbook

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 3)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao


    companion object {
        private var INSTANCE: ContactDatabase? = null
        const val DBNAME = "ContactDatabase"

        fun getInstance(context: Context):ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        DBNAME)
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}