package com.example.soundBoardApp.database

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.soundBoardApp.tools.GlobalProperties.languageLocale
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

private const val TAG = "LCM:SBDatabaseWorker"

/**
 * Worker to seed database on creation
 */
class SBDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    private val thisContext = context
    private val databaseDao = SBDatabase.getInstance(applicationContext).sBTuplesDatabaseDao()

    override suspend fun doWork(): Result = coroutineScope {

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

    fun parseStockTuples() {
        Log.i(TAG, "packagePath:" + thisContext.packageCodePath)
        val assetManager = thisContext.assets
        val assetsFolderContents = assetManager.list(TUPLES_FOLDER_NAME)
            ?: throw  IllegalArgumentException("error opening assetsFolderContents")
        for (folder in assetsFolderContents) { //go through each folder
            Log.i(TAG, "assetItem:$folder")
            databaseDao.insert(parseTupleFolderToSBDatabaseTuple(assetManager,folder))
        }
    }

    /**
     * Helper function for the parser, goes through individual tuple folders
     *
     * @param [folder] the folder holding the icon and sound to parse, exactly one of each
     *
     * @return a Sound Board Tuple
     */
    fun parseTupleFolderToSBDatabaseTuple(
        assetManager: AssetManager,
        folder: String?
    ): SBDatabaseTuple {
        var hasSound = false
        var hasIcon = false
        lateinit var soundFilePath: String
        lateinit var iconFilePath: String
        val tuplesFolderList = assetManager.list("$TUPLES_FOLDER_NAME/$folder") //TODO: replace hardcode with constant
            ?: throw IllegalArgumentException("error opening folder $TUPLES_FOLDER_NAME/$folder")
        for (file in tuplesFolderList) { //parse each file
            val tupleFileName = "$TUPLES_FOLDER_NAME/$folder/$file"
            Log.i(TAG, "asset in file: $file")

            Log.i(TAG, "FDtoAdd: $tupleFileName")
            val minimumFileExtensionLength = 4 //[.][a-zA-Z]{3} e.g. '.mp3'
            val fileName = file.toLowerCase(languageLocale)
            if (fileName.length <= minimumFileExtensionLength) throw IllegalArgumentException("file name + ext too short")
            val assetExtension = fileName.substring(
                fileName.length - minimumFileExtensionLength,
                fileName.length
            )
            when{
                FileExtensions.validSoundExtension(assetExtension) -> {
                    if (hasSound) throw IllegalArgumentException("more than one Sound file in $folder")
                    hasSound = true
                    soundFilePath = tupleFileName
                }
                FileExtensions.validImageExtension(assetExtension) -> {
                    if (hasIcon) throw IllegalArgumentException("more than one Image file in $folder")
                    hasIcon = true
                    iconFilePath = tupleFileName
                }
                else -> throw IllegalArgumentException("wrong file: '$assetExtension' type in '$folder' folder")
            }

        }
        if (!hasSound) throw IllegalArgumentException("Missing Sound File in $folder")
        if (!hasIcon) throw IllegalArgumentException("Missing Icon Image File in $folder")
        return SBDatabaseTuple(soundFilePath, iconFilePath)
    }
}