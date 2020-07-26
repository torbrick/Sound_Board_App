package com.example.soundBoardApp.tools

private const val NUM_CHAR_TRUNCATE = 200

/**
 * SoundButton represents a sound to be played and a corresponding image to be displayed
 */
data class SoundButton(
    val sound: String,
    val image: String,
    val description: String = "image that plays a sound"
) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(NUM_CHAR_TRUNCATE)
}