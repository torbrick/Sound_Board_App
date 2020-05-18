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
import com.example.soundBoardApp.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )

        binding.board1Button.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_soundBoard1_fragment)
        }

        binding.board2Button.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_soundBoard2Fragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

}
