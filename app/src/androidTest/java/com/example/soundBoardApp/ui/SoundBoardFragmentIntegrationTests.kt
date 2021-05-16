package com.example.soundBoardApp.ui

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.soundBoard.SoundBoardFragment
import com.example.soundBoardApp.soundBoard.SoundBoardViewModelFactory
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SoundBoardFragmentIntegrationTests {

    @Test
    fun addNewSoundButton() {
        val applicationContext = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext

//        val mockkRepository = mockk<SBTuplesRepository>()
//        val soundBoardFragment = SoundBoardFragment()
//        val numberOfSoundButtons =
//        val soundBoardViewModelFactory = SoundBoardViewModelFactory(mockkRepository, numberOfSoundButtons, )


    }

}