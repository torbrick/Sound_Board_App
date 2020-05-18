package com.example.soundboardapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soundBoardApp.R
import com.example.soundBoardApp.databinding.SoundBoard2FragmentBinding


class SoundBoard2Fragment : Fragment() {

    companion object {
        fun newInstance() = SoundBoard2Fragment()
    }

    private lateinit var viewModel: SoundBoard2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val soundBoard2FragmentBinding: SoundBoard2FragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.sound_board2_fragment, container, false)



        return soundBoard2FragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SoundBoard2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
