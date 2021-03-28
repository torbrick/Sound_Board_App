package com.example.soundBoardApp.utilities

import com.example.soundBoardApp.database.SBDatabaseTuple



    const val COW_IMAGE_PATH = "SBtuples/cow/noun_Cow_2761461.svg"
    const val COW_SOUND_PATH = "SBtuples/cow/Cow_Moo.mp3"
    const val DONKEY_IMAGE_PATH = "SBtuples/donkey/noun_Donkey_2761465.svg"
    const val DONKEY_SOUND_PATH = "SBtuples/donkey/19104114_donkey_by_alexm76_preview.mp3"

    const val RAM_SOUND_PATH = "SBtuples/ram/19323409_sheep_by_alenalt_preview.mp3"

    val testSBTuples = arrayListOf(
        SBDatabaseTuple(COW_SOUND_PATH,COW_IMAGE_PATH),
        SBDatabaseTuple(DONKEY_SOUND_PATH,DONKEY_IMAGE_PATH)
    )



