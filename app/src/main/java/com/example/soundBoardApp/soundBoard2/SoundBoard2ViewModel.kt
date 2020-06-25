package com.example.soundBoardApp.soundBoard2

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.soundBoardApp.R
import com.example.soundBoardApp.database.SBTuplesDatabaseDao


const val NUM_IMAGE_SOUND_BUTTONS = 10
class SoundBoard2ViewModel(
    dataBase: SBTuplesDatabaseDao,
    app: Application
) : AndroidViewModel(app) {


//    val stockTuples = dataBase.getAllTuples()
//    init {
//        for(tuple in stockTuples){
//            tuple.iconXML
//        }
//    }

    private var imageDrawable = ContextCompat.getDrawable(app, R.drawable.ic_dogbarkicon1)

    fun getDrawable(): Drawable? {
        return imageDrawable
    }
}

