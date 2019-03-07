package com.example.android.myaddressbook

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.android.dao.ContactDao
import com.example.android.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao


    companion object {
        private lateinit var INSTANCE: ContactDatabase
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