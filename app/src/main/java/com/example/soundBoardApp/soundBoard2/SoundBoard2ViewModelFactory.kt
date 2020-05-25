package com.example.soundBoardApp.soundBoard2

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soundBoardApp.database.SBTuplesDatabaseDao

/**
 * ViewModelFactory required to provide Data Base Access Object(DAO)
 */

class SoundBoard2ViewModelFactory(
    private val dataSource: SBTuplesDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SoundBoard2ViewModel::class.java)) {
            return SoundBoard2ViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}