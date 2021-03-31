package com.example.soundBoardApp.soundBoard

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.rgb
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.soundBoardApp.R
import com.example.soundBoardApp.databinding.FragmentSoundBoardBinding
import com.example.soundBoardApp.tools.DependencyInjectors


private const val TAG = "SBFragment"
private const val NUM_SOUND_BUTTONS = 15
private const val NUM_OF_BUTTON_COLUMNS = 3

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
    
    private lateinit var soundBoardFragmentBinding : FragmentSoundBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val listAdapter = SoundButtonListAdapter() //create adapter
        subscribeUi(listAdapter)

        soundBoardFragmentBinding = FragmentSoundBoardBinding.inflate(inflater, container, false).apply {
            soundBoardFragment = this@SoundBoardFragment //bind fragment for XML function calls
            buttonRecycler.adapter = listAdapter //bind adapter
            buttonRecycler.layoutManager = GridLayoutManager(activity,NUM_OF_BUTTON_COLUMNS)
        }

        return soundBoardFragmentBinding.root
    }

    /**
     *ties the lifeCycle of the livedata to this fragment,
     *Observer works as a Single Abstract Method(SAM) conversion
     */
    private fun subscribeUi(listAdapter: SoundButtonListAdapter) {
        soundBoardViewModel.viewModelSoundButtonList.observe(viewLifecycleOwner){ truncatedSoundButtonList ->
            listAdapter.submitList(truncatedSoundButtonList)
            val maxButtonsReached = (truncatedSoundButtonList.size >= soundBoardViewModel.maxNumSoundButtons)
                    soundBoardFragmentBinding.addSoundButtonFAB.apply {
                        if(maxButtonsReached) hide() else show()
                    }            
        }
    }


    // TODO: 3/31/2021 define this in XML rather than programmatically
    fun launchSoundButtonCreatorPopUp(view: View) {
        Toast.makeText(view.context, "FAB Pressed", Toast.LENGTH_SHORT).show()
        val sBCreatorPopUpWindowView = layoutInflater.inflate(R.layout.creator_sound_button, null)

        val backGroundColor = ColorDrawable(Color.BLUE)

        val windowWidth = ConstraintLayout.LayoutParams.WRAP_CONTENT
        val windowHeight = ConstraintLayout.LayoutParams.WRAP_CONTENT
        val sBCreatorPopUpWindow =
            PopupWindow(sBCreatorPopUpWindowView, windowWidth, windowHeight).apply {
                setBackgroundDrawable(backGroundColor)
                showAtLocation(view, Gravity.CENTER, 0, 0)
            }


    }

}
