package com.example.soundBoardApp.database

import com.example.soundBoardApp.utilities.getValue
import com.example.soundBoardApp.utilities.testSBTuples
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

open class SBTuplesDatabaseDaoTest : SBDataBaseTest() {

    @Test
    fun testInsertGetSBTuple() = runBlocking {
        val loadedTuples  = ArrayList(testSBTuples.map { it.copy()})
       assertNotSame(loadedTuples.component1(), testSBTuples.component1()) //maker sure we have a deep copy

        loadedTuples.forEach {
            sbTuplesDao.insert(it)
        }

        val retrievedTuples = sbTuplesDao.getAllTuples()
        assertNotNull(getValue(retrievedTuples).forEachIndexed { index, tuple ->
            //ID is autogenerated by ROOM, so only want to test for preservation of URIs
            assertTrue(tuple.targetsSame(loadedTuples[index]))
        })
    }

}