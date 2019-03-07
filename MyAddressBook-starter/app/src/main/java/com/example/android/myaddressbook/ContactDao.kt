package com.example.android.myaddressbook

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {
    @get:Query("SELECT * FROM contact")
    val all: List<Contact>

    @Query("SELECT * FROM contact WHERE uid IN (:contactIds)")
    fun loadAllByIds(contactIds: IntArray): List<Contact>

    @Query("SELECT * FROM contact WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Contact

    @Insert
    fun insertAll(vararg contacts: Contact)

    @Delete
    fun delete(contact: Contact)
}