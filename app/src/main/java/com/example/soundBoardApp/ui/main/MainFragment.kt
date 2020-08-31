package com.example.soundBoardApp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.soundBoardApp.R
import com.example.soundBoardApp.database.SBDatabase
import com.example.soundBoardApp.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = SBDatabase.getInstance(application).sBTuplesDatabaseDao

        val viewModelFactory = MainViewModelFactory(dataSource,application)

        val mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        //for binding of buttons in layout
        //TODO: binding.mainTrackerViewModel = mainViewModel


        binding.board1Button.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_soundBoard1_fragment)
        }

        binding.board2Button.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_soundBoard2Fragment)
        }

        binding.SoundBoardButton.setOnClickListener{
            findNavController().navigate(R.id.action_titleFragment_to_soundBoardFragment)
        }
        //TODO: implement observers for navigation

        return binding.root
    }

    /*
    onActivityCreated is not currently necessary
     */
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//    }

}
