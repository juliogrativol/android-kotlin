package com.example.android.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.android.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    fun listAll():LiveData<List<Contact>>

    @Insert
    fun insert(contact: Contact)

    @Insert
    fun insert(contacts: List<Contact>)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("DELETE FROM Contact")
    abstract fun deleteAll()
}