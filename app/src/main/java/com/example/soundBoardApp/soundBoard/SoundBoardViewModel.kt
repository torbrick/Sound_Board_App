package com.example.soundBoardApp.soundBoard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.tools.SoundButton

private const val TAG = "LCM: SBViewModel"
private const val DEFAULT_IMAGE = "AndroidSVG_logo.svg"
private const val DEFAULT_SOUND = "wowa.mp3"

/**
 * @param numSoundButtons the number of SoundButtons the board should start with,
 * fills with default image/sound if insufficient quantity in sbTuplesRepository
 */

class SoundBoardViewModel(
    sbTuplesRepository: SBTuplesRepository,
    numSoundButtons: Int
) : ViewModel() {
    val liveSoundButtonList: LiveData<List<SoundButton>>
    //var numSoundButtonsLiveData = MutableLiveData(numSoundButtons)
    init {
        Log.d(TAG, "SoundBoardViewModel init")
       liveSoundButtonList = Transformations.map(sbTuplesRepository.getAllSoundButtons()){it.take(numSoundButtons)}
    }
}