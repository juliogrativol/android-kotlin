package com.example.android.myaddressbook

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class ContactViewModel(application: Application): AndroidViewModel(application) {

    // não é privado, porque será observado
    val contactsList: LiveData<List<Contact>>
    private val repository: ContactRepository


    init {
        val database = ContactDatabase.getInstance(application.applicationContext)
        val dao = database.contactDao()
        repository = ContactRepository(dao)
        contactsList = repository.listAll()
    }


    fun insert(contact: Contact){
        DoAsync {
            repository.insert(contact)
        }
    }

    fun delete(contact: Contact){
        DoAsync {
            repository.delete(contact)
        }
    }

    class DoAsync(val action: ()->Unit): AsyncTask<Unit, Unit, Unit>() {

        init {
            execute()
        }

        override fun doInBackground(vararg params: Unit?) {
            action()
        }
    }
}