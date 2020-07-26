package com.example.soundBoardApp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.soundBoardApp.database.SBTuplesDatabaseDao

private const val TAG = "MainViewModel"

class MainViewModel(
    val database: SBTuplesDatabaseDao,
    app: Application) : AndroidViewModel(app) {
    // TODO: 6/24/2020 implement viewmodel if necessary
}
