package com.example.soundBoardApp.database

import com.example.soundBoardApp.utilities.getValue
import org.junit.Test

import org.junit.Before

open class SBTuplesRepositoryTest : SBDataBaseTest() {
    protected lateinit var testSBTuplesRepo : SBTuplesRepository

    @Before
    fun setUpRepo(){
        testSBTuplesRepo = SBTuplesRepository.getInstance(sbTuplesDao)
    }

    @Test
    fun getSoundButtons() {
        val testButtons = getValue(testSBTuplesRepo.getAllSoundButtons())

    }

    @Test
    fun getAllSoundButtons() {
    }
}