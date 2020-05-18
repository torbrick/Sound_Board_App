package com.example.soundBoardApp

import android.content.Context
import android.content.res.AssetFileDescriptor
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.soundBoardApp.database.SBTuple
import com.example.soundBoardApp.database.SBTuplesDatabase
import com.example.soundBoardApp.database.SBTuplesDatabaseDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


class SBTuplesDatabaseTest {


    @RunWith(AndroidJUnit4::class)
    class SBTuplesDatabaseTest {

        private lateinit var sbTuplesDao: SBTuplesDatabaseDao
        private lateinit var db: SBTuplesDatabase
        //https://stackoverflow.com/questions/8605611/get-context-of-test-project-in-android-junit-test-case
        lateinit var instrumentationContext: Context
        @Before
        fun setup() {
            instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        }

        fun createDb() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            // Using an in-memory database because the information stored here disappears when the
            // process is killed.
            db = Room.inMemoryDatabaseBuilder(context, SBTuplesDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
            sbTuplesDao = db.sbTuplesDatabaseDAO
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        fun insertAndGetTuple() {
//            val testXMLfilePath = R.drawable.ic_dogbarkicon1
//            val testMP3filePath= R.raw.testdogbarksound1
//            //val testSoundMP3: AssetFileDescriptor = instrumentationContext.resources.openRawResourceFd(afdTestXMLResID)
//            //val testIconXML: AssetFileDescriptor = instrumentationContext.resources.openRawResourceFd(afdTestXMLResID)
//            val tuple = SBTuple(0,testXMLfilePath,testMP3filePath)
//            sbTuplesDao.insert(tuple)
//            val tupleList = sbTuplesDao.getAllTuples()
//            for (tuple in tupleList) {
//                //TODO: implement actual tests
//                Assert.assertNotNull(tuple)
//            }
        }
    }
}