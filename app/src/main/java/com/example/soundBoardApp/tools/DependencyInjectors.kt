package com.example.soundBoardApp.tools

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import androidx.annotation.IntegerRes
import com.example.soundBoardApp.database.SBDatabase
import com.example.soundBoardApp.database.SBDatabaseWorkerFactory
import com.example.soundBoardApp.database.SBTuplesDatabaseDao
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.soundBoard.SoundBoardViewModelFactory

object DependencyInjectors {
    private fun getSBTuplesDao(context: Context): SBTuplesDatabaseDao {
        return SBDatabase.getInstance(context.applicationContext).sBTuplesDatabaseDao()
    }
    private fun getSBTuplesRepository(context: Context): SBTuplesRepository{
        return  SBTuplesRepository.getInstance(
            getSBTuplesDao(context)
        )
    }

    fun provideSoundBoardViewModelFactory(context: Context, numSoundButtons: Int, application: Application): SoundBoardViewModelFactory {
        return SoundBoardViewModelFactory(getSBTuplesRepository(context), numSoundButtons, application)
    }

    fun provideSBDatabaseWorkerFactory(context: Context): SBDatabaseWorkerFactory {
        return SBDatabaseWorkerFactory(getSBTuplesDao(context))
    }


}