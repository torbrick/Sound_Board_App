package com.example.soundBoardApp.database

import com.example.soundBoardApp.tools.GlobalProperties.languageLocale
import java.util.*

const val TUPLES_FOLDER_NAME = "SBtuples" //name of the folder that holds tuples folders

object FileExtensions {
    private val soundExtensions = listOf(".mp3").map {it.toUpperCase(languageLocale)
    }
    private val imageExtensions = listOf(".xml", ".svg").map { it.toUpperCase(languageLocale) }

    fun validSoundExtension(extension: String): Boolean {
        return soundExtensions.contains(extension.toUpperCase(languageLocale))
    }

    fun validImageExtension(extension: String): Boolean {
        return imageExtensions.contains(extension.toUpperCase(languageLocale))
    }
}

