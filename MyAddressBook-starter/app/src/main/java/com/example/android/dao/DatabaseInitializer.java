/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dao;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.model.Contact;
import com.example.android.myaddressbook.ContactDatabase;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final ContactDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final ContactDatabase db) {
        populateWithTestData(db);
    }

    private static Contact addContact(final ContactDatabase db, final String firstName, final String lastName, String email) {
        Contact contact = new Contact(firstName, lastName, email);
        db.contactDao().insert(contact);
        return contact;
    }

    private static void populateWithTestData(ContactDatabase db) {


        try {
            addContact(db, "julio cesar 1", "grativol", "teste@teste.com.br");
            Thread.sleep(DELAY_MILLIS);
            addContact(db, "julio cesar 2" , "grativol", "teste@teste.com.br");
            Thread.sleep(DELAY_MILLIS);
            addContact(db, "julio cesar 3", "grativol", "teste@teste.com.br");
            Thread.sleep(DELAY_MILLIS);
            addContact(db, "julio cesar 4", "grativol", "teste@teste.com.br");
            Thread.sleep(DELAY_MILLIS);
            addContact(db, "julio cesar 5", "grativol", "teste@teste.com.br");
            Log.d("DB", "Added contacts");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ContactDatabase mDb;

        PopulateDbAsync(ContactDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }
    }
}