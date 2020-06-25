package com.example.soundBoardApp.soundBoard2

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soundBoardApp.R
import com.example.soundBoardApp.database.SBDatabase
import com.example.soundBoardApp.databinding.SoundBoard2FragmentBinding


class SoundBoard2Fragment : Fragment() {

    companion object {
        fun newInstance() = SoundBoard2Fragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val soundBoard2FragmentBinding: SoundBoard2FragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.sound_board2_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = SBDatabase.getInstance(application).sBTuplesDatabaseDao

        val viewModelFactory = SoundBoard2ViewModelFactory(dataSource, application)
        val soundBoard2ViewModel = ViewModelProviders.of(this, viewModelFactory).get(SoundBoard2ViewModel::class.java)

        //TODO: bind the dog sound/icon to button for testing
        soundBoard2FragmentBinding.soundButton1.setImageDrawable(soundBoard2ViewModel.getDrawable())



        return soundBoard2FragmentBinding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(SoundBoard2ViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
