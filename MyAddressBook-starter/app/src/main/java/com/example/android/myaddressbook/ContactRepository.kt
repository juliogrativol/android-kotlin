package com.example.android.myaddressbook

import android.arch.lifecycle.LiveData

class ContactRepository(private val contactDAO: ContactDao) {

    fun shouldFetchData(): Boolean{
        return false
    }

    fun listAll(): LiveData<List<Contact>>{
        return contactDAO.listAll()
    }

    fun insert(contact: Contact){
        contactDAO.insert(contact)
    }

    fun delete(contact: Contact){
        contactDAO.delete(contact)
    }
}