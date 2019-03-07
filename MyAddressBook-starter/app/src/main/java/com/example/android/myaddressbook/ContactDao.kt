package com.example.android.myaddressbook

import android.arch.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Query("SELECT * FROM Pharmacy")
    fun listAll():LiveData<List<Contact>>

    @Insert
    fun insert(contact: Contact)

    @Insert
    fun insert(contacts: List<Contact>)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}