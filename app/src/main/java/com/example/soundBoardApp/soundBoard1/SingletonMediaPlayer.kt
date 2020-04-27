package com.example.soundBoardApp.soundBoard1

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast

object SingletonMediaPlayer {


    private lateinit var buttonSoundMP: MediaPlayer

    fun playSound(thisContext: Context, rawResID: Int) {
        fun parseSoundUri(rawResID: Int): Uri {
            return Uri.parse("android.resource://" + thisContext.packageName + "/" + rawResID)
        }

        if (!(::buttonSoundMP.isInitialized)) {
            buttonSoundMP = MediaPlayer.create(thisContext, parseSoundUri(rawResID))
            buttonSoundMP.start()
        } else {
            buttonSoundMP.apply {
                reset()
                setDataSource(thisContext, parseSoundUri(rawResID))
                prepare()
                start()
            }
        }

    }
}
