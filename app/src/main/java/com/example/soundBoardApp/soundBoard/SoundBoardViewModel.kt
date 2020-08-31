package com.example.soundBoardApp.soundBoard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.tools.SoundButton

private const val TAG = "SBViewModel"
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
//    private var soundButtonList: ArrayList<SoundButton>
//
//
//    val liveSoundButtonList: MutableLiveData<ArrayList<SoundButton>>
    val testList : ArrayList<SoundButton>
    val liveTestList : MutableLiveData<ArrayList<SoundButton>>

    init {
        testList = ArrayList<SoundButton>().apply {
            repeat(numSoundButtons){this.add(SoundButton(DEFAULT_SOUND, DEFAULT_IMAGE))}}
        liveTestList = MutableLiveData(testList)
    }


//   init {
//        val buttonArraylist = ArrayList<SoundButton>().apply {
//            repeat(numSoundButtons) { this.add(SoundButton(DEFAULT_SOUND, DEFAULT_IMAGE)) }
//        }
//        liveSoundButtonList = sbTuplesRepository.getAllSoundButtons() ?: buttonArraylist
//       val repoButtonList = sbTuplesRepository.soundButtons.value
//       val
//        for (index in 0..numSoundButtons) {
//            val buttonToAdd = if (numSoundButtons >= repoButtonList.size) {
//                _soundButtonList.value[index]
//            } else {
//                SoundButton(DEFAULT_SOUND, DEFAULT_IMAGE)
//            }
//            _soundButtonList.value?.add(buttonToAdd)
//        }
//    }
}