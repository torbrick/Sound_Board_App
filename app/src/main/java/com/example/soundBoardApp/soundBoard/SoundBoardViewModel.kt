package com.example.soundBoardApp.soundBoard

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.tools.SoundButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "LCM: SBViewModel"

/**
 * @param maxNumSoundButtons maximum number of buttons for this SoundBoard
 */

class SoundBoardViewModel(
    sbTuplesRepository: SBTuplesRepository,
    val maxNumSoundButtons: Int,
    application: Application
) : AndroidViewModel(application) {
    val viewModelSoundButtonList: LiveData<List<SoundButton>>
    val newLiveSoundButton : MutableLiveData<SoundButton> by lazy { MutableLiveData(SoundButton()) }
    val newImageFileName : MutableLiveData<String> = MutableLiveData("image file path")
    private val context: Context
        get() = getApplication()

    init {
        Log.d(TAG, "SoundBoardViewModel init")
       viewModelSoundButtonList = Transformations.map(sbTuplesRepository.getAllSoundButtons()){
                it.take(maxNumSoundButtons)
            }

    }

    fun loadImageURI(imageUri: Uri) {

        newLiveSoundButton.value?.apply {
            imagePath = imageUri.toString()
        }

        if (newLiveSoundButton.value == null) {
            newLiveSoundButton.value = SoundButton().apply { imagePath = imageUri.toString()}
        }

    }



    // TODO: 4/1/2021 complete this stub
    fun copyImageFromUri(uri: Uri) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                context.contentResolver.openInputStream(uri)?.let {
//                    copyImageFromStream(it, imagesFolder)
//                    _notification.postValue("Image copied")
                }
            }
        }
    }
}