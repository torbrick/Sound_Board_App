package com.example.soundBoardApp.tools

import android.content.Context
import androidx.annotation.IntegerRes
import com.example.soundBoardApp.database.SBDatabase
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.soundBoard2.SoundBoard2Fragment
import com.example.soundBoardApp.soundBoard2.SoundBoard2ViewModelFactory

object DependencyInjectors {
    private fun getSBTuplesRepository(context: Context): SBTuplesRepository{
        return  SBTuplesRepository.getInstance(
            SBDatabase.getInstance(context).sBTuplesDatabaseDao
        )
    }

    fun provideSoundBoardViewModelFactory(context: Context, numSoundButtons: Int):SoundBoard2ViewModelFactory{
        return SoundBoard2ViewModelFactory(getSBTuplesRepository(context), numSoundButtons)
    }
}