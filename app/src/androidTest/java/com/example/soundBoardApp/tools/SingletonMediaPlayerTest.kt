package com.example.soundBoardApp.tools

//import com.example.soundBoardApp.utilities.
import android.content.Context
import android.content.res.AssetManager
import androidx.test.core.app.ApplicationProvider
import com.example.soundBoardApp.R
import com.example.soundBoardApp.utilities.DONKEY_SOUND_PATH
import com.example.soundBoardApp.utilities.RAM_SOUND_PATH
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class SingletonMediaPlayerTest {

    private lateinit var context: Context
    private lateinit var assetManager: AssetManager
    private val latchWaiter = CountDownLatch(1)

    @Before
    fun setup() {
        /**
         * Still don't quite understand the difference between these two contexts
         */
        context = ApplicationProvider.getApplicationContext()
        //context = InstrumentationRegistry.getInstrumentation().targetContext
        assetManager = context.assets
    }

    @Test
    fun playSoundRawResTest() {

        val wowESound = R.raw.wowe
        val wowASound = R.raw.wowa

        assertTrue(SingletonMediaPlayer.run {
            playSound(context, wowESound)
            isPlaying()
        }.also {
            waitForPlayingToFinish()
        })

    }

    @Test
    fun playSoundFileDescriptorTest() {
        val donkeySoundFileDescriptor = assetManager.openFd(DONKEY_SOUND_PATH)

        assertTrue(SingletonMediaPlayer.run {
            playSound(donkeySoundFileDescriptor)
            isPlaying()
        }.also {
            waitForPlayingToFinish()
        })

        val ramSoundFileDescriptor = assetManager.openFd(RAM_SOUND_PATH)

        assertTrue(SingletonMediaPlayer.run {
            playSound(ramSoundFileDescriptor)
            isPlaying()
        }.also {
            waitForPlayingToFinish()
        })

    }

    private fun waitForPlayingToFinish() {
        while (SingletonMediaPlayer.isPlaying()) {
            latchWaiter.await(100, TimeUnit.MILLISECONDS)
        }
    }


}