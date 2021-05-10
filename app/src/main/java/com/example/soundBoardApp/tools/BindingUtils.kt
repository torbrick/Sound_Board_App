package com.example.soundBoardApp.tools

import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.caverock.androidsvg.SVG
import com.example.soundBoardApp.R
import com.example.soundBoardApp.soundBoard.SoundBoardFragment

const val BUTTON_WIDTH_PIXELS = 250
const val BUTTON_HEIGHT_PIXELS = 250

@BindingAdapter("buttonImage")
fun ImageView.setButtonImage(button: SoundButton?){
    button?.let {
        val svg = SVG.getFromAsset(requireNotNull(this.context).assets,
            it.imagePath)
        val svgAsDrawable = PictureDrawable(svg.renderToPicture(BUTTON_WIDTH_PIXELS,BUTTON_HEIGHT_PIXELS))
        Glide.with(this.context)
            .load(svgAsDrawable)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(this)
    }
}

@BindingAdapter("soundButtonClick")
fun ImageView.setButtonClick(button: SoundButton?) {
    button?.apply {
        setOnClickListener {
            //TODO: Need to change this so that we support both fileDescriptors and URIs (from local storage)
            val soundFileDescriptor = context.assets.openFd(this.soundPath)
            SingletonMediaPlayer.playSound(soundFileDescriptor)
        }
    }

}

//@BindingAdapter("cancelCreatorPopUpWindow")
//fun Button.setClickCancelCreatorPopUpWindow(fragment : SoundBoardFragment){
//    setOnClickListener {
//        Toast.makeText(this.context, "cancel button pressed", Toast.LENGTH_SHORT).show()
//        //fragment.closeSoundButtonCreatorPopUp()
//    }
//}