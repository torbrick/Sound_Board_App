package com.example.soundBoardApp.database

import com.example.soundBoardApp.tools.GlobalProperties.languageLocale

const val TUPLES_FOLDER_NAME = "SBtuples" //name of the folder that holds tuples folders

object FileExtensions {
    private val supportedSoundExtensions = listOf(".mp3").map {it.toUpperCase(languageLocale)
    }
    private val supportedImageExtensions = listOf(".xml", ".svg").map { it.toUpperCase(languageLocale) }

    fun validSoundExtension(extension: String): Boolean {
        return supportedSoundExtensions.contains(extension.toUpperCase(languageLocale))
    }

    fun validImageExtension(extension: String): Boolean {
        return supportedImageExtensions.contains(extension.toUpperCase(languageLocale))
    }
}

