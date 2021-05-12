package com.example.soundBoardApp.soundBoard

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.tools.GlobalProperties.imageFolderPath
import com.example.soundBoardApp.tools.SoundButton
import com.example.soundBoardApp.tools.copyImageFromStream
import com.example.soundBoardApp.tools.generateFileNameFromURI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

private const val TAG = "LCM: SBViewModel"
private const val newImageFileNameDefaultText = "image file path"
private const val newSoundFileNameDefaultText = "sound file path"

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
    private val _newImageFileName : MutableLiveData<String> = MutableLiveData(newImageFileNameDefaultText)
    val newImageFileName : LiveData<String>
    get() = _newImageFileName
    private val _newSoundFileName : MutableLiveData<String> = MutableLiveData(newSoundFileNameDefaultText)
    val newSoundFileName : LiveData<String>
    get() = _newSoundFileName



    private val context: Context
        get() = getApplication()
    private val imagesFolder: File by lazy { getImagesFolder(context) }

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
                    copyImageFromStream(it, imagesFolder,generateFileNameFromURI(uri))
                    Log.d(TAG, "copyImageFromUri: Copied Image")
                }
            }
        }
    }

    private fun getImagesFolder(context: Context): File {
//        val xml = context.resources.getXml(R.xml.filepaths)
//
//        val attributes = getAttributesFromXmlNode(xml, FILEPATH_XML_KEY)
//
//        val folderPath = attributes["path"]
//            ?: error("You have to specify the sharable directory in res/xml/filepaths.xml")

        return File(context.filesDir, imageFolderPath).also {
            if (!it.exists()) {
                it.mkdir()
            }
        }
    }

    var newImageFileUri : Uri? = null
        private set
    var newSoundFileUri : Uri? = null
        private set

   fun filePathNamesAndUrisReset(){
       _newImageFileName.value = newImageFileNameDefaultText
       newImageFileUri = null
       _newSoundFileName.value = newSoundFileNameDefaultText
       newSoundFileUri = null
    }

    fun setNewImageFileNameAndUri(uri: Uri){
        newImageFileUri = uri
        _newImageFileName.value = uri.toString()
    }

    fun setNewSoundFileNameAndUri(uri:Uri){
        newSoundFileUri = uri
        _newSoundFileName.value = uri.toString()
    }
}