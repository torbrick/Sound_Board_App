package com.example.soundBoardApp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.soundBoardApp.database.SBTuple
import com.example.soundBoardApp.database.SBTuplesDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModel"

class MainViewModel(
    val database: SBTuplesDatabaseDao,
    app: Application) : AndroidViewModel(app) {
    // TODO: 6/24/2020 implement viewmodel if necessary
}
