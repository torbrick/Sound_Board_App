package com.example.soundBoardApp.tools

import java.util.Locale


object GlobalProperties {
    var languageLocale: Locale = Locale.US
    const val imageFolderPath = "userImages/"

}

val SUPPORTED_IMAGE_MIME_TYPES =
    arrayOf("image/bmp", "image/gif", "image/jpeg", "image/png", "image/svg+xml0", "image/webp")
val SUPPORTED_SOUND_MIME_TYPES =
    arrayOf("audio/aac", "audio/midi", "audio/x-midi", "audio/mpeg", "audio/wav", "audio/webm")