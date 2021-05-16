package com.example.soundBoardApp.database

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.soundBoardApp.tools.SoundButton

/**
 * @property   [soundButtons] the cached list of tuples
 */

class SBTuplesRepository private constructor(private val sBTuplesDao: SBTuplesDatabaseDao) {

    var soundButtons = Transformations.map(sBTuplesDao.getAllTuples()){it.asDomainModel()}
        private set


    /**
     * @return the current list of SoundButtons and updates the cache
     */
    fun getAllSoundButtons() = Transformations.map(sBTuplesDao.getAllTuples()){it.asDomainModel()}.also { soundButtons = it }

    fun addSoundButton(soundButton: SoundButton){
        sBTuplesDao.insert( SBDatabaseTuple.asStorageModel(soundButton))
    }


    companion object {
        // For Singleton instantiation
        @Volatile private var instance: SBTuplesRepository? = null
        fun getInstance(sBTuplesDao: SBTuplesDatabaseDao) =
            instance ?: synchronized(this) {
                instance ?: SBTuplesRepository(sBTuplesDao).also { instance = it }
            }
    }

}