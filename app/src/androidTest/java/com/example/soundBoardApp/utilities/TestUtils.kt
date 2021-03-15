package com.example.soundBoardApp.utilities

import com.example.soundBoardApp.database.SBDatabaseTuple



    private val cowImagePath = "SBtuples/cow/noun_Cow_2761461.svg"
    private val cowSoundPath = "SBtuples/cow/Cow_Moo.mp3"
    private val donkeyImagePath = "SBtuples/donkey/noun_Donkey_2761465.svg"
    private val donkeySoundPath = "SBtuples/donkey/19104114_donkey_by_alexm76_preview.mp3"
    val testSBTuples = arrayListOf(
        SBDatabaseTuple(cowSoundPath,cowImagePath),
        SBDatabaseTuple(donkeySoundPath,donkeyImagePath)
    )



