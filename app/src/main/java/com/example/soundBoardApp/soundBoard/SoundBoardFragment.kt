package com.example.soundBoardApp.soundBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        val soundBoardFragmentBinding: FragmentSoundBoardBinding =
            FragmentSoundBoardBinding.inflate(inflater, container, false)

        val listAdapter = SoundButtonListAdapter() //create adapter
        soundBoardFragmentBinding.buttonRecycler.adapter = listAdapter //bind adapter




        //val testSBlist = soundBoardViewModel.soundButtonList.value
        val manager = GridLayoutManager(activity,3)
        soundBoardFragmentBinding.buttonRecycler.layoutManager = manager


//        soundBoardViewModel.liveTestList.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                listAdapter.submitList(it)
//            }  })


        /**
         *ties the lifeCycle of the livedata to this fragment,
         *Observer works as a Single Abstract Method(SAM) conversion
         */
        soundBoardViewModel.liveSoundButtonList.observe(viewLifecycleOwner, Observer {
            it?.let {
                listAdapter.submitList(it)
            }  })

//        val svg = SVG.getFromAsset(requireNotNull(this.activity).assets,"SBtuples/cow/noun_Cow_2761461.svg")
//        soundBoardFragmentBinding.soundButton10.setBackgroundResource(0)
//        val buttonWidthPixels = soundBoardFragmentBinding.soundButton10.layoutParams.width
//        val buttonHeightPixels = soundBoardFragmentBinding.soundButton10.layoutParams.height
//        var svgAsDrawable = PictureDrawable(svg.renderToPicture(buttonWidthPixels,buttonHeightPixels))
//        soundBoardFragmentBinding.soundButton10.setImageDrawable(svgAsDrawable)

        return soundBoardFragmentBinding.root
    }
}
