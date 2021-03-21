package com.example.soundBoardApp.database

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters

class SBDatabaseWorkerFactory(
    private val sBTuplesDao: SBTuplesDatabaseDao
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when(workerClassName) {
            (SBDatabaseWorker::class.java.name) ->
                SBDatabaseWorker(appContext, workerParameters, sBTuplesDao)
            // Return null, so that the base class can delegate to the default WorkerFactory.
            else -> null
        }
    }
}