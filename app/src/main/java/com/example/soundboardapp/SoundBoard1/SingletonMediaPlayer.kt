package com.example.soundboardapp.SoundBoard1

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import com.example.soundboardapp.R

object SingletonMediaPlayer: Application() {


    private val buttonSoundDefault = R.raw.achievementunlocked

    private val buttonSound1 = R.raw.wowa
    private val buttonSound2 = R.raw.wowb
    private val buttonSound3 = R.raw.wowc
    private val buttonSound4 = R.raw.wowd
    private val buttonSound5 = R.raw.wowe
    private val buttonSound6 = R.raw.wowf
    private val buttonSound7 = R.raw.wowg
    private val buttonSound8 = R.raw.wowh


    private val thisContext: android.content.Context = this.applicationContext

    private var buttonSoundMP: MediaPlayer? = MediaPlayer.create(thisContext, parseSoundUri(buttonSoundDefault))

    fun playSound() {
        buttonSoundMP?.start()
        val toast = Toast.makeText(thisContext, "playSound", Toast.LENGTH_SHORT)
        toast.show()

    }


    private fun parseSoundUri(rawResID: Int):android.net.Uri{
        return  Uri.parse("android.resource://" + thisContext.packageName + "/" + rawResID)
    }
}
