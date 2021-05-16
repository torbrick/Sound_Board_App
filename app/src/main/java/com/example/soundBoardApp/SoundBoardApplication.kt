package com.example.soundBoardApp

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import com.example.soundBoardApp.database.SBDatabaseWorkerFactory
import com.example.soundBoardApp.database.SBTuplesDatabaseDao
import com.example.soundBoardApp.tools.DependencyInjectors

class SoundBoardApplication() : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration {
        val myWorkerFactory = DelegatingWorkerFactory()
        myWorkerFactory.addFactory(
            DependencyInjectors.provideSBDatabaseWorkerFactory(this)
        )
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(myWorkerFactory)
            .build()
    }

}