package com.example.soundBoardApp.database

import android.content.res.AssetFileDescriptor
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SoundBoard_Image_Sound_Tuples_Table")
data class SBTuple(
//SOUND MP3 File Path
    @ColumnInfo(name = "sound_effect_MP3")
    val soundMP3: String,
//ICON XML File Path
    @ColumnInfo(name = "icon_image_XML")
    val iconXML: String,

    @PrimaryKey(autoGenerate = true)
var tupleID: Long = 0L

)