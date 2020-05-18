package com.example.soundBoardApp.ui.main

import android.app.Application
import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.soundBoardApp.SingletonMediaPlayer
import java.io.IOException

private const val TAG = "MainViewModel"

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val thisContext: android.content.Context = this.getApplication()

    init {
        parseStockTuples()

    }

    private fun parseStockTuples() {
        Log.i(TAG, "packagePath:" + thisContext.packageCodePath)
        var assetManager = thisContext.assets
        val assetsFolderContents = assetManager.list("SBtuples")
            ?: throw  IllegalArgumentException("error opening assetsFolderContents")
        for (folder in assetsFolderContents) { //go through each folder
            Log.i(TAG, "assetItem:$folder")
            val assetsInFolder = assetManager.list("SBtuples/$folder")
                ?: throw IllegalArgumentException("error opening folder SBtuples/$folder")

            //return list of assetsInFolders
            for (file in assetsInFolder) { //go through each file
                Log.i(TAG, "asset in file: $file")
                var thisAssetFD = try {
                    assetManager.openFd("SBtuples/$folder/$file")
                } catch (e: IOException) {
                    //TODO: handle this more gracefully, maybe ask for intput or log error and skip over
                    throw IOException("Error opening file: $file")
                }
                Log.i(TAG, "FDtoAdd: SBtuples/$folder/$file")
                val minimumFileExtensionLength = 4; //[.][a-zA-Z]{3}
                val fileName = file.toLowerCase()
                if (fileName.length > minimumFileExtensionLength) {

                    val assetExtension = fileName.substring(
                        fileName.length - minimumFileExtensionLength,
                        fileName.length
                    )
                    when (assetExtension) {
                        ".xml" -> println("xml")
                        //TODO: remove this sound playing
                        ".mp3" -> {
                            println("mp3")
                            SingletonMediaPlayer.playSound(thisContext, thisAssetFD)
                        }
                        else -> throw IllegalArgumentException("wrong file type in $folder folder")
                    }
                }


            }
        }
    }


}
