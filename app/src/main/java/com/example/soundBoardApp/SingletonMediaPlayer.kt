package com.example.soundBoardApp

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri

object SingletonMediaPlayer {


    private lateinit var buttonSoundMP: MediaPlayer
    private const val DEFAULT_SOUND = R.raw.achievementunlocked

    fun playSound(thisContext: Context, rawResID: Int) {
        val soundUri = Uri.parse("android.resource://" + thisContext.packageName + "/" + rawResID)

        playUri(
            thisContext,
            soundUri
        )

    }

    fun playSound(thisContext: Context, uriString: String) {
        val soundUri = Uri.parse(uriString)
        playUri(
            thisContext,
            soundUri
        )
    }

    fun playSound(thisContext: Context, soundAssetFileDescriptor: AssetFileDescriptor) {
        if (!(SingletonMediaPlayer::buttonSoundMP.isInitialized)) {
            //TODO: revise default constructor
            buttonSoundMP = MediaPlayer.create(thisContext,
                DEFAULT_SOUND
            )
        }
        buttonSoundMP.apply {
            reset()
            setDataSource(soundAssetFileDescriptor.fileDescriptor)
            prepare()
            start()
        }
    }

    private fun playUri(thisContext: Context, soundUri: Uri) {
        if (!(SingletonMediaPlayer::buttonSoundMP.isInitialized)) {
            buttonSoundMP = MediaPlayer.create(thisContext, soundUri)
            buttonSoundMP.start()
        } else {
            buttonSoundMP.apply {
                reset()
                setDataSource(thisContext, soundUri)
                prepare()
                start()
            }
        }
    }


}
