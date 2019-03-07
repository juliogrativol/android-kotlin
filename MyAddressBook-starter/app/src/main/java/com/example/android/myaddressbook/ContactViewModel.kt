package com.example.android.myaddressbook

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.android.dao.DatabaseInitializer
import com.example.android.model.Contact

class ContactViewModel(application: Application): AndroidViewModel(application) {

    // não é privado, porque será observado
    val contactsList: LiveData<List<Contact>>
    private val repository: ContactRepository
    private lateinit var mDb: ContactDatabase


    init {
        createDb();
        val database = ContactDatabase.getInMemoryDatabase(application.applicationContext)
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

    fun createDb() {
        mDb = ContactDatabase.getInMemoryDatabase(this.getApplication<Application>())

        //Populate it with initial data
        DatabaseInitializer.populateAsync(mDb)
    }
}