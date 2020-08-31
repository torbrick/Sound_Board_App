package com.example.soundBoardApp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soundBoardApp.tools.SoundButton

/**
 * each tuple has a sound and an icon
 */
/**
 *
 * @property soundMP3 File Path to an [SOUND_FILE_EXTENSION] for opening an AssetFileDescriptor with AssetManager
 *
 * @property iconXML File Path to an [IMAGE_FILE_EXTENSION] for opening an AssetFileDescriptor with AssetManager
 */

@Entity(tableName = "SoundBoard_Image_Sound_Tuples_Table")
data class SBDatabaseTuple(
//SOUND MP3 File Path for opening an AssetFileDescriptor with AssetManager
    @ColumnInfo(name = "sound_effect_MP3")
    val soundMP3: String,

    @ColumnInfo(name = "icon_image_XML")
    val iconXML: String,

    @PrimaryKey(autoGenerate = true)
var tupleID: Long = 0L

)

fun List<SBDatabaseTuple>.asDomainModel(): List<SoundButton>{
    return map {
        SoundButton(
            soundPath = it.soundMP3,
            imagePath = it.iconXML
        )
    }
}

fun SBDatabaseTuple.asDomainModel(): SoundButton{
    return SoundButton(
            soundPath = soundMP3,
            imagePath = iconXML
        )
}