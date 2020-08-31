package com.example.soundBoardApp.soundBoard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soundBoardApp.database.SBTuplesRepository

/**
 * ViewModelFactory required to provide Data Base Access Object(DAO)
 * @param dataSource the repository for Sound Board Tuples
 */

class SoundBoardViewModelFactory(
    private val dataSource: SBTuplesRepository,
    private val numSoundButtons: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SoundBoardViewModel::class.java)) {
            return SoundBoardViewModel(dataSource, numSoundButtons) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}