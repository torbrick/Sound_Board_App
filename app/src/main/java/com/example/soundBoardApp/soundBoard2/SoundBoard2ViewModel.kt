package com.example.soundBoardApp.soundBoard2

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.soundBoardApp.database.SBDatabaseTuple
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.tools.SoundButton
import kotlin.IllegalArgumentException


private const val TAG = "SB2ViewModel"
private const val DEFAULT_IMAGE = "AndroidSVG_logo.svg"
private const val DEFAULT_SOUND = "wowa.mp3"

class SoundBoard2ViewModel(
    private val sbTuplesRepository: SBTuplesRepository,
        private val numSoundButtons: Int
) : ViewModel() {
    private val liveDataButtonArray = Array<LiveData<SoundButton>>(numSoundButtons){ MutableLiveData(SoundButton(DEFAULT_SOUND, DEFAULT_IMAGE))}
    init {

        val soundButtonList = sbTuplesRepository.soundButtons.value
            ?: throw IllegalArgumentException("No SoundButtonList")
        for (buttonListIndex in 1..soundButtonList.size) {
            //replace old button value in array with new button value from repository
            liveDataButtonArray[buttonListIndex] = Transformations.map(liveDataButtonArray[buttonListIndex]) {
                soundButtonList[buttonListIndex]
            }


        }
    }

//    val stockTuples = dataBase.getAllTuples()
//    init {
//        for(tuple in stockTuples){
//            tuple.iconXML
//        }
//    }

   // private var imageDrawable = ContextCompat.getDrawable(app, R.drawable.ic_dogbarkicon1)

//
//    fun getDrawable(): Drawable? {
//        return imageDrawable
//    }
//
//    fun filePathSVG(): String {
//        return iconXMLFilePath
//    }
}

