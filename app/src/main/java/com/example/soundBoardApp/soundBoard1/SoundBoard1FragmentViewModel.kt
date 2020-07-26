package com.example.soundBoardApp.soundBoard1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.soundBoardApp.R
import com.example.soundBoardApp.tools.SingletonMediaPlayer


private const val TAG = "SB1FragmentVM"

class SoundBoard1FragmentViewModel(app: Application) : AndroidViewModel(app) {


    private val thisContext: android.content.Context = this.getApplication()

    //    private val buttonSoundDefault = R.raw.achievementunlocked
    private val buttonSound1 = R.raw.wowa
    private val buttonSound2 = R.raw.wowb
    private val buttonSound3 = R.raw.wowc
    private val buttonSound4 = R.raw.wowd
    private val buttonSound5 = R.raw.wowe
    private val buttonSound6 = R.raw.wowf
    private val buttonSound7 = R.raw.wowg
    private val buttonSound8 = R.raw.wowh


    init {
            }



    fun playButton1Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound1)
    }

    fun playButton2Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound2)
    }

    fun playButton3Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound3)
    }

    fun playButton4Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound4)
    }

    fun playButton5Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound5)
    }

    fun playButton6Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound6)
    }

    fun playButton7Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound7)
    }

    fun playButton8Sound() {
        SingletonMediaPlayer.playSound(thisContext, buttonSound8)
    }


}
