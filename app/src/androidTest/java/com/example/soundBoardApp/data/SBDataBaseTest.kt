package com.example.soundBoardApp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.soundBoardApp.database.SBDatabase
import com.example.soundBoardApp.database.SBTuplesDatabaseDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class SBDataBaseTest {
    private lateinit var sbDatabase: SBDatabase
    protected lateinit var sbTuplesDao: SBTuplesDatabaseDao

    @get:Rule
    var instantTaskExecutorRule =
        InstantTaskExecutorRule() //to move things off of background threads for testing

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        sbDatabase = Room.inMemoryDatabaseBuilder(context, SBDatabase::class.java).build()
        sbTuplesDao = sbDatabase.sBTuplesDatabaseDao()
    }

    @After
    fun closeDb() {
        sbDatabase.close()
    }
}