package com.example.soundboardapp.SoundBoard1

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soundboardapp.R
import com.example.soundboardapp.databinding.FragmentSoundBoard1Binding


class SoundBoard1Fragment : Fragment() {

    companion object {
        fun newInstance() =
            SoundBoard1Fragment()
    }

    private lateinit var viewModel: SoundBoard1FragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val soundBoard1FragmentBinding: FragmentSoundBoard1Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sound_board1, container, false
        )

        val soundBoard1ViewModel =
            ViewModelProviders.of(this).get(SoundBoard1FragmentViewModel::class.java)

        soundBoard1FragmentBinding.Sound1Button.setOnClickListener {
            soundBoard1ViewModel.playButton1Sound()
            soundBoard1ViewModel.playSound()
        }

        soundBoard1FragmentBinding.Sound2Button.setOnClickListener {
            soundBoard1ViewModel.playButton2Sound()
        }

        soundBoard1FragmentBinding.Sound3Button.setOnClickListener {
            soundBoard1ViewModel.playButton3Sound()
        }

        soundBoard1FragmentBinding.Sound4Button.setOnClickListener {
            soundBoard1ViewModel.playButton4Sound()
        }

        soundBoard1FragmentBinding.Sound5Button.setOnClickListener {
            soundBoard1ViewModel.playButton5Sound()
        }

        soundBoard1FragmentBinding.Sound6Button.setOnClickListener {
            soundBoard1ViewModel.playButton6Sound()
        }

        soundBoard1FragmentBinding.Sound7Button.setOnClickListener {
            soundBoard1ViewModel.playButton7Sound()
        }

        soundBoard1FragmentBinding.Sound8Button.setOnClickListener {
            soundBoard1ViewModel.playButton8Sound()
        }





        return soundBoard1FragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SoundBoard1FragmentViewModel::class.java)
        // TODO: Use the ViewModel

    }

}
