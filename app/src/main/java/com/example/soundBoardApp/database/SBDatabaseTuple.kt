package com.example.soundBoardApp.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soundBoardApp.tools.SoundButton

/**
 * each tuple has a sound and an icon
 */
/**
 *
 * @property soundPath File Path to an [SOUND_FILE_EXTENSION] for opening an AssetFileDescriptor with AssetManager
 *
 * @property iconPath File Path to an [IMAGE_FILE_XML] for opening an AssetFileDescriptor with AssetManager
 */

@Entity(tableName = "SoundBoard_Image_Sound_Tuples_Table")
data class SBDatabaseTuple(
//SOUND MP3 File Path for opening an AssetFileDescriptor with AssetManager
    @ColumnInfo(name = "sound_effect_path")
    val soundPath: String,

    @ColumnInfo(name = "icon_image_path")
    val iconPath: String,

    //put last in data class to allow easy dataClass(deceleration,deceleration)
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "tupleID")
    var tupleID: Long = 0L

)

// TODO: 9/1/2020 go over and explain map vs switchMap
fun List<SBDatabaseTuple>.asDomainModel(): List<SoundButton>{
    return map {
        SoundButton(
            soundPath = it.soundPath,
            imagePath = it.iconPath
        )
    }
}

fun SBDatabaseTuple.asDomainModel(): SoundButton{
    return SoundButton(
            soundPath = soundPath,
            imagePath = iconPath
        )
}

fun SBDatabaseTuple.targetsSame(compareTuple: SBDatabaseTuple): Boolean{
    return (this.iconPath == compareTuple.iconPath) && (this.soundPath == compareTuple.soundPath)
}