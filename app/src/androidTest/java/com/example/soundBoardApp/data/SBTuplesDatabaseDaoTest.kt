package com.example.soundBoardApp.data

import com.example.soundBoardApp.database.targetsSame
import com.example.soundBoardApp.utilities.getValue
import com.example.soundBoardApp.utilities.testSBTuples
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class SBTuplesDatabaseDaoTest : SBDataBaseTest() {
    @Test
    fun testInsertGetSBTuple() = runBlocking {
        val testSBTuples2  = ArrayList(testSBTuples.map { it.copy()})
       assertNotSame(testSBTuples2.component1(), testSBTuples.component1()) //maker sure we have a deep copy

        testSBTuples2.forEach {
            sbTuplesDao.insert(it)
        }

        val retrievedTuples = sbTuplesDao.getAllTuples()
        assertNotNull(getValue(retrievedTuples).forEachIndexed { index, tuple ->
            //ID is autogenerated by ROOM, so only want to test for preservation of URIs
            assertTrue(tuple.targetsSame(testSBTuples2[index]))
        })
    }

}