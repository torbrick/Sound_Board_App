package com.example.soundBoardApp.soundBoard2

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.soundBoardApp.R
import com.example.soundBoardApp.database.SBTuplesDatabaseDao

class SoundBoard2ViewModel(
    app1: SBTuplesDatabaseDao,
    app: Application
) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private var imageDrawable = ContextCompat.getDrawable(app, R.drawable.ic_dogbarkicon1)

    fun getDrawable(): Drawable? {
        return imageDrawable
    }
}

