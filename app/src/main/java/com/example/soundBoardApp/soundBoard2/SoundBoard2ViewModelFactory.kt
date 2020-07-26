package com.example.soundBoardApp.soundBoard2

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soundBoardApp.database.SBTuplesDatabaseDao
import com.example.soundBoardApp.database.SBTuplesRepository

/**
 * ViewModelFactory required to provide Data Base Access Object(DAO)
 * @param dataSource the repository for Sound Board Tuples
 */

class SoundBoard2ViewModelFactory(
    private val dataSource: SBTuplesRepository,
    private val numSoundButtons: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SoundBoard2ViewModel::class.java)) {
            return SoundBoard2ViewModel(dataSource, numSoundButtons) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}