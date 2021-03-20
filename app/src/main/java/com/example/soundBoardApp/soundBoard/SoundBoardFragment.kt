package com.example.soundBoardApp.soundBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.soundBoardApp.databinding.FragmentSoundBoardBinding
import com.example.soundBoardApp.tools.DependencyInjectors


private const val TAG = "SBFragment"
private const val NUM_SOUND_BUTTONS = 15

class SoundBoardFragment : Fragment() {
    /**
     * uses a delegate to create viewModel via custom View Model Factory
     */
    private val soundBoardViewModel: SoundBoardViewModel by viewModels {
        //val application = requireNotNull(this.activity).application
        val application = requireContext()
        DependencyInjectors.provideSoundBoardViewModelFactory(
            application,
            NUM_SOUND_BUTTONS
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val soundBoardFragmentBinding: FragmentSoundBoardBinding =
            FragmentSoundBoardBinding.inflate(inflater, container, false)

        val listAdapter = SoundButtonListAdapter() //create adapter
        soundBoardFragmentBinding.buttonRecycler.adapter = listAdapter //bind adapter
        subscribeUi(listAdapter)


        val manager = GridLayoutManager(activity,3)
        soundBoardFragmentBinding.buttonRecycler.layoutManager = manager


        return soundBoardFragmentBinding.root
    }

    /**
     *ties the lifeCycle of the livedata to this fragment,
     *Observer works as a Single Abstract Method(SAM) conversion
     */
    private fun subscribeUi(listAdapter: SoundButtonListAdapter) {
        soundBoardViewModel.liveSoundButtonList.observe(viewLifecycleOwner){
            soundButtonList -> listAdapter.submitList(soundButtonList)
        }
    }
}
