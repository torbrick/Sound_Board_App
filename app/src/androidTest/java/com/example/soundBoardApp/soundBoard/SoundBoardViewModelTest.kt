package com.example.soundBoardApp.soundBoard

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.soundBoardApp.database.SBDataBaseTest
import com.example.soundBoardApp.database.SBTuplesRepository
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

private const val NUM_SOUND_BUTTONS = 15

@RunWith(JUnit4::class)
class SoundBoardViewModelTest : SBDataBaseTest() {
    //private lateinit var applicationContext: Context
    //val SBViewModel = SoundBoardViewModel()

    // @get:Rule
    // var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //val applicationContext = ApplicationProvider.getApplicationContext()
        val application = Application()
        val testSBTuplesRepo = SBTuplesRepository.getInstance(sbTuplesDao)

        val soundBoardTestViewModel =
            SoundBoardViewModel(testSBTuplesRepo, NUM_SOUND_BUTTONS, application)
    }

}