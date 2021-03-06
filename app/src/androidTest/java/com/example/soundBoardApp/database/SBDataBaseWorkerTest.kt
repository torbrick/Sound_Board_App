package com.example.soundBoardApp.database

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.soundBoardApp.tools.DependencyInjectors
import com.example.soundBoardApp.utilities.getValue
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RefreshMainDataWorkTest : SBDataBaseTest() {

    private lateinit var applicationContext: Context
    private lateinit var workManager: WorkManager
    private lateinit var assetManager: AssetManager
    private lateinit var testDBWorker: SBDatabaseWorker

    @Before
    fun setup() {
        applicationContext = ApplicationProvider.getApplicationContext()
        workManager = WorkManager.getInstance(applicationContext)
        assetManager = applicationContext.assets
        val myWorkerFactory = DependencyInjectors.provideSBDatabaseWorkerFactory(applicationContext)
        testDBWorker = TestListenableWorkerBuilder<SBDatabaseWorker>(applicationContext).setWorkerFactory(myWorkerFactory).build()
    }

    @Test
    fun parseStockTuples2() {
        testDBWorker.parseStockTuples(sbTuplesDao)
    }

    @Test
    fun parseStockTuplesTest() {
        val assetsFolderContents = assetManager.list(TUPLES_FOLDER_NAME)
        Assert.assertNotNull(assetsFolderContents)
        val sBTuplesFromFiles = ArrayList<SBDatabaseTuple>()
        for (folder in assetsFolderContents!!) { //go through each folder
            val tupleFetched = testDBWorker.parseTupleFolderToSBDatabaseTuple(assetManager,folder)
            sBTuplesFromFiles.add(tupleFetched.copy())
        Log.d(this::parseStockTuplesTest.name,tupleFetched.toString())
            sbTuplesDao.insert(tupleFetched)
        }
        // sBTuplesFromFiles = testDBWorker.parseStockTuples()
        val retrievedTuplesLiveData = sbTuplesDao.getAllTuples()
        val retrievedTuplesValue = getValue(retrievedTuplesLiveData)

        Assert.assertEquals(retrievedTuplesValue.size, sBTuplesFromFiles.size)

        Assert.assertNotNull(retrievedTuplesValue.forEachIndexed { index, tuple ->
            //ID is autogenerated by ROOM, so only want to test for preservation of URIs
            Assert.assertTrue(tuple.targetsSame(sBTuplesFromFiles[index]))
        })
    }


    @Test
    fun testRefreshMainDataWork() {
        // Start the work synchronously
        val result = testDBWorker.startWork().get()
        Assert.assertThat(result, CoreMatchers.`is`(ListenableWorker.Result.success()))
    }

}
