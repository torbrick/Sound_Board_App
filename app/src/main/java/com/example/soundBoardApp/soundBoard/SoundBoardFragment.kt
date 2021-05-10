package com.example.soundBoardApp.soundBoard

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
//import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.soundBoardApp.R
import com.example.soundBoardApp.databinding.CreatorSoundButtonBinding
import com.example.soundBoardApp.databinding.FragmentSoundBoardBinding
import com.example.soundBoardApp.tools.DependencyInjectors
import kotlinx.android.synthetic.main.creator_sound_button.view.*
import kotlinx.android.synthetic.main.fragment_sound_board.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.observe


private const val TAG = "SBFragment"
private const val NUM_SOUND_BUTTONS = 15
private const val NUM_OF_BUTTON_COLUMNS = 3
val ACCEPTED_MIMETYPES = arrayOf("image/jpeg", "image/png")

class SoundBoardFragment : Fragment() {
    /**
     * uses a delegate to create viewModel via custom View Model Factory
     */
    private val soundBoardViewModel: SoundBoardViewModel by viewModels {
        //val application = requireNotNull(this.activity).application
        val application = requireContext()
        DependencyInjectors.provideSoundBoardViewModelFactory(
            application,
            NUM_SOUND_BUTTONS,
            Application()
        )
    }

    private lateinit var soundBoardFragmentBinding: FragmentSoundBoardBinding
    private lateinit var creatorSoundButtonBinding : CreatorSoundButtonBinding
    private lateinit var popUpWindowView : View
    private var soundButtonCreatorPopUpWindow : PopupWindow? = null

    private val selectImage = registerForActivityResult(GetContentWithExtras()) { uri ->
        uri?.let {
            soundBoardViewModel.copyImageFromUri(uri)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val listAdapter = SoundButtonListAdapter() //create adapter
        subscribeUi(listAdapter)

        creatorSoundButtonBinding = CreatorSoundButtonBinding.inflate(inflater,container,false).also {
            it.soundBoardFragment = this
        }


        soundBoardFragmentBinding =
            FragmentSoundBoardBinding.inflate(inflater, container, false).apply {
                soundBoardFragment = this@SoundBoardFragment //bind fragment for XML function calls
                buttonRecycler.adapter = listAdapter //bind adapter
                buttonRecycler.layoutManager = GridLayoutManager(activity, NUM_OF_BUTTON_COLUMNS)
            }

        return soundBoardFragmentBinding.root
    }

    // TODO: 3/31/2021 define this in XML rather than programmatically
    fun launchSoundButtonCreatorPopUp(view: View) {
        //val myView = view.rootView as ViewGroup
        //val sBCreatorPopUpWindowView = layoutInflater.inflate(R.layout.creator_sound_button, myView  )
       // creatorSoundButtonBinding = CreatorSoundButtonBinding.inflate(layoutInflater, null, false)
       // creatorSoundButtonBinding.soundBoardFragment = this
        popUpWindowView = layoutInflater.inflate(R.layout.creator_sound_button, null  ).apply {
//            soundBoardViewModel.newImageFileName.observe(viewLifecycleOwner,Observer<String?>{
//                textFieldImageFilePath.setText(it)
//            })
//
            soundBoardViewModel.newImageFileName.observe(viewLifecycleOwner,Observer{myString: String ->
                textFieldImageFilePath.setText(myString)})

        }



        val windowWidth = ConstraintLayout.LayoutParams.WRAP_CONTENT
        val windowHeight = ConstraintLayout.LayoutParams.WRAP_CONTENT
        soundButtonCreatorPopUpWindow = PopupWindow(popUpWindowView, windowWidth, windowHeight).apply {
                setBackgroundDrawable(ColorDrawable(Color.BLUE))
                showAtLocation(view, Gravity.CENTER, 0, 0)
                soundBoardFragmentBinding.createSoundButtonFab.hide()
            }

        fun selectImage(){
            selectImage.launch(ACCEPTED_MIMETYPES)
        }


        popUpWindowView.apply {
            cancelButton.setOnClickListener { closeSoundButtonCreatorPopUp() }
            editImagePathButton.setOnClickListener {selectImage() }
        }



    }

    private fun closeSoundButtonCreatorPopUp() {
        soundButtonCreatorPopUpWindow?.dismiss()
        displayFAB()
    }

    /**
     * showsFAB only if max buttons not yet reached
     */
    private fun displayFAB(){
        val numButtons = soundBoardViewModel.viewModelSoundButtonList.value?.size ?: 0
        val maxButtonsReached = (numButtons>= soundBoardViewModel.maxNumSoundButtons)
        soundBoardFragmentBinding.createSoundButtonFab.apply {
            if (maxButtonsReached) hide() else show()
        }
    }


    /**
     *ties the lifeCycle of the livedata to this fragment,
     *Observer works as a Single Abstract Method(SAM) conversion
     */
    private fun subscribeUi(listAdapter: SoundButtonListAdapter) {
        soundBoardViewModel.viewModelSoundButtonList.observe(viewLifecycleOwner) { viewModelSoundButtonListData ->
            listAdapter.submitList(viewModelSoundButtonListData)
            displayFAB()
        }
    }


    class GetContentWithExtras : ActivityResultContract<Array<String>, Uri?>(){
        private val contentGetter by lazy{ ActivityResultContracts.GetContent() }

        override fun createIntent(context: Context, input: Array<String>): Intent {
            return contentGetter.createIntent(context, "*/*")
                .putExtra(Intent.EXTRA_MIME_TYPES, input)
        }


        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return contentGetter.parseResult(resultCode,intent)
        }
    }



}
