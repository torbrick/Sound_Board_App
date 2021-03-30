package com.example.soundBoardApp.soundBoard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.tools.SoundButton

private const val TAG = "LCM: SBViewModel"

/**
 * @param maxNumSoundButtons maximum number of buttons for this SoundBoard
 */

class SoundBoardViewModel(
    sbTuplesRepository: SBTuplesRepository,
    val maxNumSoundButtons: Int
) : ViewModel() {
    val viewModelSoundButtonList: LiveData<List<SoundButton>>
    init {
        Log.d(TAG, "SoundBoardViewModel init")
       viewModelSoundButtonList = Transformations.map(sbTuplesRepository.getAllSoundButtons()){
                it.take(maxNumSoundButtons)
            }

    }
}