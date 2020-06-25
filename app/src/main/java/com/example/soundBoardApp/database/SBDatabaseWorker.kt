package com.example.soundBoardApp.database

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

private const val TAG = "SBDatabaseWorker"
private const val TUPLES_FOLDER_NAME = "SBtuples" //name of the folder that holds tuples folders

/**
 * Worker to seed database on creation
 */
class SBDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    private val thisContext = context
    private val databaseDao = SBDatabase.getInstance(thisContext).sBTuplesDatabaseDao

    override suspend fun doWork(): Result = coroutineScope {

       // TODO("Not yet implemented")
        try {
            parseStockTuples()
            Result.success()
        }catch (exception: Exception){
            Log.e(TAG, "Error seeding database", exception)
            Result.failure()
        }

    }

    /**
     * Seeds the database from the file structure
     */

    private fun parseStockTuples() {
        Log.i(TAG, "packagePath:" + thisContext.packageCodePath)
        val assetManager = thisContext.assets
        val assetsFolderContents = assetManager.list(TUPLES_FOLDER_NAME)
            ?: throw  IllegalArgumentException("error opening assetsFolderContents")
        for (folder in assetsFolderContents) { //go through each folder
            Log.i(TAG, "assetItem:$folder")
            databaseDao.insert(parseTupleFolderToDao(assetManager,folder))
        }
    }

    /**
     * Helper function for the parser, goes through individual tuple folders
     *
     * @param [folder] the folder holding the icon and sound to parse, exactly one of each
     *
     * @return a Sound Board Tuple
     */
    private fun parseTupleFolderToDao(
        assetManager: AssetManager,
        folder: String?
    ): SBTuple {
        var hasSoundMP3 = false
        var hasIconXML = false
        lateinit var soundMP3FilePath: String
        lateinit var iconXMLFilePath: String
        val tuplesFolderList = assetManager.list("SBtuples/$folder")
            ?: throw IllegalArgumentException("error opening folder SBtuples/$folder")
        for (file in tuplesFolderList) { //parse each file
            val tupleFileName = "SBtuples/$folder/$file"
            Log.i(TAG, "asset in file: $file")

            Log.i(TAG, "FDtoAdd: $tupleFileName")
            val minimumFileExtensionLength = 4 //[.][a-zA-Z]{3} e.g. '.mp3'
            val fileName = file.toLowerCase()
            if (fileName.length <= minimumFileExtensionLength) throw IllegalArgumentException("file name + ext too short")
            val assetExtension = fileName.substring(
                fileName.length - minimumFileExtensionLength,
                fileName.length
            )
            when (assetExtension) {
                ".mp3" -> {
                    if (hasSoundMP3) throw IllegalArgumentException("more than one sound MP3 file in $folder")
                    hasSoundMP3 = true
                    soundMP3FilePath = tupleFileName
                }
                ".xml" -> {
                    if (hasIconXML) throw IllegalArgumentException("more than one icon XML file in $folder")
                    hasIconXML = true
                    iconXMLFilePath = tupleFileName
                }
                else -> throw IllegalArgumentException("wrong file type in $folder folder")
            }


        }
        if (!hasSoundMP3) throw IllegalArgumentException("Missing Mp3 in $folder")
        if (!hasIconXML) throw IllegalArgumentException("Missing Icon XML in $folder")
        return SBTuple(soundMP3FilePath, iconXMLFilePath)
    }
}