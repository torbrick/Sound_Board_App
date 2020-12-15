package com.example.soundBoardApp.tools

import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.caverock.androidsvg.SVG
import com.example.soundBoardApp.R

//
//@BindingAdapter("buttonImage")
//fun ImageView.setButtonImage(button: SoundButton?){
//    button?.let {
////        val svg = SVG.getFromAsset(requireNotNull(this.context).assets, it.imagePath)
//        val svg = SVG.getFromAsset(requireNotNull(this.context).assets, "SBtuples/cow/noun_Cow_2761461.svg")
//        this.setBackgroundResource(0)
//        val buttonWidthPixels = this.layoutParams.width
//        val buttonHeightPixels = this.layoutParams.height
//        val svgAsDrawable = PictureDrawable(svg.renderToPicture(10,10))
//        this.setImageDrawable(svgAsDrawable)
//    }
//}

const val BUTTON_WIDTH_PIXELS = 250
const val BUTTON_HEIGHT_PIXELS = 250

@BindingAdapter("buttonImage")
fun ImageView.setButtonImage(button: SoundButton?){
    button?.let {
//        val svg = SVG.getFromAsset(requireNotNull(this.context).assets, it.imagePath)
        val svg = SVG.getFromAsset(requireNotNull(this.context).assets, "SBtuples/cow/noun_Cow_2761461.svg")
        val svgAsDrawable = PictureDrawable(svg.renderToPicture(BUTTON_WIDTH_PIXELS,BUTTON_HEIGHT_PIXELS))
        Glide.with(this.context)
            .load(svgAsDrawable)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(this)

//        this.setBackgroundResource(0)
//        val buttonWidthPixels = this.layoutParams.width
//        val buttonHeightPixels = this.layoutParams.height
//        val svgAsDrawable = PictureDrawable(svg.renderToPicture(10,10))
//        this.setImageDrawable(svgAsDrawable)
    }
}




//fun ImageButton.setButtonImage(view: View, button: SoundButton?){
//    button?.let {
//        val svg = SVG.getFromAsset(requireNotNull(view.context).assets, it.imagePath)
//    }
//}

