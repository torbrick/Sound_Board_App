package com.example.soundboardapp.SoundBoard1

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import com.example.soundboardapp.R

object SingletonMediaPlayer {


    private lateinit var buttonSoundMP: MediaPlayer

    fun playSound(thisContext: Context, rawResID: Int) {
        fun parseSoundUri(rawResID: Int): android.net.Uri {
            return Uri.parse("android.resource://" + thisContext.packageName + "/" + rawResID)
        }

        if(!(::buttonSoundMP.isInitialized)){
            buttonSoundMP = MediaPlayer.create(thisContext, parseSoundUri(rawResID))
            buttonSoundMP.start()
        }else{
            buttonSoundMP?.apply {
                reset()
                setDataSource(thisContext, parseSoundUri(rawResID))
                prepare()
                start()
            }
        }


        val toast = Toast.makeText(thisContext, "playSound", Toast.LENGTH_SHORT)
        toast.show()




    }



}
