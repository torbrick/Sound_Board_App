/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//I copy pasted most of this class from a google tutorial
package com.example.soundBoardApp.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

const val DATABASE_NAME = "soundBoard_tuples_database"

@Database(entities = [SBDatabaseTuple::class], version = 1, exportSchema = false)
abstract class SBDatabase : RoomDatabase() {
    //connect database to DAO
    abstract fun sBTuplesDatabaseDao(): SBTuplesDatabaseDao
    /**
     * Define a companion object, this allows us to add functions on the SleepDatabase class.
     *
     * For example, clients can call `SleepDatabase.getInstance(context)` to instantiate
     * a new SleepDatabase.
     */
    companion object {
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */
        @Volatile
        private var INSTANCE: SBDatabase? = null

        /**
         * Helper function to get the database.
         *
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         *
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         *
         * This is an example of a simple Singleton pattern that takes another Singleton as an
         * argument in Kotlin.
         *
         * To learn more about Singleton read the wikipedia article:
         * https://en.wikipedia.org/wiki/Singleton_pattern
         *
         * @param context The application context Singleton, used to get access to the filesystem.
         */
        fun getInstance(context: Context): SBDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {

                    instance = buildDatabase(context)
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }

        private fun buildDatabase(context: Context): SBDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SBDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    //this is the Call Back that gets called on creation
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val workRequest = OneTimeWorkRequestBuilder<SBDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(workRequest)
                    }
                })
                // Wipes and rebuilds instead of migrating if no Migration object.
                // Migration is not part of this lesson. You can learn more about
                // migration with Room in this blog post:
                // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}