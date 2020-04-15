package com.example.soundboardapp.SoundBoard1

import android.app.Application
import android.media.MediaPlayer
import com.example.soundboardapp.R
import androidx.lifecycle.AndroidViewModel

class SoundBoard1FragmentViewModel(app: Application) : AndroidViewModel(app) {


    // TODO: Implement the ViewModel
    private var achievementSoundMP: MediaPlayer? = MediaPlayer.create(this.getApplication(), R.raw.achievementunlocked)
    init {

    }

    fun playSound() {
        achievementSoundMP?.start()
    }

}
