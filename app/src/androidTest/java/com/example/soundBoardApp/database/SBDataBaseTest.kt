package com.example.soundBoardApp.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class SBDataBaseTest {
    private lateinit var sbInMemoryDatabase: SBDatabase
    protected lateinit var sbTuplesDao: SBTuplesDatabaseDao

    @get:Rule
    var instantTaskExecutorRule =
        InstantTaskExecutorRule() //to move things off of background threads for testing

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        sbInMemoryDatabase = Room.inMemoryDatabaseBuilder(context, SBDatabase::class.java).build()
        sbTuplesDao = sbInMemoryDatabase.sBTuplesDatabaseDao()
    }


    @After
    fun closeDb() {
        sbInMemoryDatabase.close()
    }
}