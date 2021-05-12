package com.example.soundBoardApp.tools

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import java.lang.Exception

// TODO: 3/28/2021 add individual volume control for sounds

private const val TAG = "SingletonMediaPlayer"

object SingletonMediaPlayer {

    /**
     * @buttonSoundMP is nullable because the resulting object from MediaPlayer is a platform type
     */
    private var buttonSoundMP: MediaPlayer? = MediaPlayer().apply {
        setOnCompletionListener {
            //TODO: remove the setOnCompletionListener if we end up not actually needing it for anything
            Log.d(TAG, "On Completion Listener TRIGGERED")
            stop()
        }
    }



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

    fun playSound(soundAssetFileDescriptor: AssetFileDescriptor) {
        buttonSoundMP?.apply {
            reset()
            try {
                //if you don't specific start and end positions, it plays all assets
                val startPosition = soundAssetFileDescriptor.startOffset
                val endPosition = soundAssetFileDescriptor.length.also {
                    if (it < 1) Log.d(
                        TAG,
                        "playSound: $soundAssetFileDescriptor endPosition of Unkown Length"
                    )
                }

                setDataSource(soundAssetFileDescriptor.fileDescriptor,startPosition,endPosition)
                prepare()
                start()
            }catch(e: Exception){
                Log.d(TAG, "playSound: threw exception $e")
            }
        }
    }

    fun isPlaying() : Boolean{
        return buttonSoundMP?.isPlaying ?: false
    }

    fun stopPlaying(){
        buttonSoundMP?.apply {
            if(isPlaying) {
                stop()
                Log.d(TAG, "stopPlaying: Was playing and told to stop")
            }

        }
    }

    fun playUri(thisContext: Context, soundUri: Uri) {
            buttonSoundMP?.apply {
                reset()
                try{
                    Log.d(TAG, "playUri: $soundUri")
                    setDataSource(thisContext, soundUri)
                    prepare()
                    start()
                }catch (e :Exception){
                    Log.d(TAG, "playUri: thew exception $e")
                }
            }
        }

}
