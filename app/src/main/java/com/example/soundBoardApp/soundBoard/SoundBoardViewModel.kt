package com.example.soundBoardApp.soundBoard

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.example.soundBoardApp.database.SBTuplesRepository
import com.example.soundBoardApp.tools.GlobalProperties.imageFolderPath
import com.example.soundBoardApp.tools.SingletonMediaPlayer
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
    private val sbTuplesRepository: SBTuplesRepository,
    val maxNumSoundButtons: Int,
    application: Application
) : AndroidViewModel(application) {
    val viewModelSoundButtonList: LiveData<List<SoundButton>>

    private val newSoundButton : NewSoundButton by lazy {NewSoundButton()}

    private val _newImageFileName : MutableLiveData<String> = MutableLiveData(newImageFileNameDefaultText)
    val newImageFileName : LiveData<String>
    get() = _newImageFileName

    private val _newSoundFileName : MutableLiveData<String> = MutableLiveData(newSoundFileNameDefaultText)
    val newSoundFileName : LiveData<String>
    get() = _newSoundFileName



    private val applicationContext: Context
        get() = getApplication()
    private val imagesFolder: File by lazy { getImagesFolder(applicationContext) }

    init {
        Log.d(TAG, "SoundBoardViewModel init")
       viewModelSoundButtonList = Transformations.map(sbTuplesRepository.getAllSoundButtons()){
                it.take(maxNumSoundButtons)
            }

    }



    // TODO: 4/1/2021 complete this stub
    fun copyImageFromUri(uri: Uri) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                applicationContext.contentResolver.openInputStream(uri)?.let {
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



   fun filePathsReset(){
       _newImageFileName.value = newImageFileNameDefaultText
       _newSoundFileName.value = newSoundFileNameDefaultText
       newSoundButton.reset()
    }

    fun setNewImageFile(uri: Uri){
        newSoundButton.newImageFileUri = uri
        _newImageFileName.value = uri.toString()
    }

    fun setNewSoundFile(uri:Uri){
        newSoundButton.newSoundFileUri = uri
        _newSoundFileName.value = uri.toString()
    }

    fun playSoundFilePreview(context: Context) {
        newSoundButton.newSoundFileUri?.let { soundUri ->
            SingletonMediaPlayer.playUri(context,soundUri)
        }
    }


    private inner class NewSoundButton(){
        var newImageFileUri: Uri? = null
        var newSoundFileUri: Uri? = null


        fun save() {
            val newSoundButton = createSoundButton(newImageFileUri, newSoundFileUri)
            newSoundButton?.let {
                sbTuplesRepository.addSoundButton(it)
                this.reset()
            }
        }

        fun reset() {
            newImageFileUri = null
            newSoundFileUri = null
        }

        private fun createSoundButton(imageUri: Uri?, soundUri: Uri?) : SoundButton? {
            //if image is not already in folder
            //if sound is not already in folder
            var nullUri = false
            if(imageUri == null) {
                Log.d(TAG, "createSoundButton: imageUri is null")
                nullUri = true
            }
            if(soundUri == null){
                Log.d(TAG, "createSoundButton: soundUri is null")
                nullUri = true
            }

            return if (nullUri) null
            else SoundButton(soundUri.toString(), imageUri.toString())

        }

    }
}