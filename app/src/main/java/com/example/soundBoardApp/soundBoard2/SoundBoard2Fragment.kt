package com.example.soundBoardApp.soundBoard2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.example.soundBoardApp.R
import com.example.soundBoardApp.databinding.SoundBoard2FragmentBinding
import com.example.soundBoardApp.tools.DependencyInjectors


private const val TAG = "SB2Fragment"
private const val NUM_SOUND_BUTTONS = 10

class SoundBoard2Fragment : Fragment() {

    private val soundBoard2ViewModel: SoundBoard2ViewModel by viewModels {
        val application = requireNotNull(this.activity).application
        DependencyInjectors.provideSoundBoardViewModelFactory(
            application,
            NUM_SOUND_BUTTONS
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val soundBoard2FragmentBinding: SoundBoard2FragmentBinding =
            SoundBoard2FragmentBinding.inflate(inflater, container, false)

//        //TODO: bind the dog sound/icon to button for testing
//        soundBoard2FragmentBinding.soundButton2.setImageDrawable(soundBoard2ViewModel.getDrawable())
//        soundBoard2FragmentBinding.soundButton1.setImageAsset(soundBoard2ViewModel.filePathSVG())
        return soundBoard2FragmentBinding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(SoundBoard2ViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
