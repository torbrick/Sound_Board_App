package com.example.soundBoardApp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.soundBoardApp.database.SBTuple
import com.example.soundBoardApp.database.SBTuplesDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModel"

class MainViewModel(
    val database: SBTuplesDatabaseDao,
    app: Application) : AndroidViewModel(app) {
    private val thisContext: android.content.Context = this.getApplication()
    val uiScope = CoroutineScope(Dispatchers.Main)

    init {
        parseStockTuples()

    }

    private fun parseStockTuples() {
        Log.i(TAG, "packagePath:" + thisContext.packageCodePath)
        val assetManager = thisContext.assets
        val assetsFolderContents = assetManager.list("SBtuples")
            ?: throw  IllegalArgumentException("error opening assetsFolderContents")
        for (folder in assetsFolderContents) { //go through each folder
            Log.i(TAG, "assetItem:$folder")
            val assetsInFolder = assetManager.list("SBtuples/$folder")
                ?: throw IllegalArgumentException("error opening folder SBtuples/$folder")
            //TODO: figure out callback errors for Coroutines
            uiScope.launch {
                insert(parseTupleFolderToDao(assetsInFolder, folder))
            }
        }
    }

    private fun parseTupleFolderToDao(
        tuplesFolderList: Array<String>,
        folder: String?
    ): SBTuple {
        var hasSoundMP3 = false
        var hasIconXML = false
        lateinit var soundMP3FilePath : String
        lateinit var iconXMLFilePath : String
        for (file in tuplesFolderList) { //parse each file
            val tupleFileName = "SBtuples/$folder/$file"
            Log.i(TAG, "asset in file: $file")

//            var thisAssetFD = try {
//                assetManager.openFd(tupleFileName)
//            } catch (e: IOException) {
//                //TODO: handle this more gracefully, maybe ask for intput or log error and skip over
//                throw IOException("Error opening file: $file")
//            }
            Log.i(TAG, "FDtoAdd: $tupleFileName")
            val minimumFileExtensionLength = 4 //[.][a-zA-Z]{3}
            val fileName = file.toLowerCase()
            if (fileName.length <= minimumFileExtensionLength) throw IllegalArgumentException("file name + ext too short")
            val assetExtension = fileName.substring(
                fileName.length - minimumFileExtensionLength,
                fileName.length
            )
            when (assetExtension) {
                //TODO: remove this sound playing
                ".mp3" -> {
                    if (hasSoundMP3) throw IllegalArgumentException("more than one sound MP3 files")
                    hasSoundMP3 = true
                    soundMP3FilePath = tupleFileName
                    println("mp3")
                   // SingletonMediaPlayer.playSound(thisContext, thisAssetFD)
                }
                ".xml" -> {
                    if (hasIconXML) throw IllegalArgumentException("more than one icon XML files")
                    hasIconXML = true
                    iconXMLFilePath = tupleFileName
                        println("xml")
                }
                else -> throw IllegalArgumentException("wrong file type in $folder folder")
            }


        }
        return SBTuple(soundMP3FilePath,iconXMLFilePath)
    }

    private suspend fun insert(tuple: SBTuple){
        withContext(Dispatchers.IO){
            database.insert(tuple)
        }

    }


}