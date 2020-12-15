package com.example.soundBoardApp.soundBoard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soundBoardApp.databinding.ListSoundButtonBinding
import com.example.soundBoardApp.tools.SoundButton


class SoundButtonListAdapter :
    ListAdapter<SoundButton, SoundButtonListAdapter.SoundButtonViewHolder>(SoundButtonDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundButtonViewHolder {
        return SoundButtonViewHolder.createFromParent(parent)
    }


    override fun onBindViewHolder(soundButtonViewHolder: SoundButtonViewHolder, position: Int) {
        val curButton = getItem(position)
        soundButtonViewHolder.bindSoundButtonToViewHolder(curButton)
    }



    class SoundButtonViewHolder private constructor(val listSoundButtonBinding: ListSoundButtonBinding) :
        RecyclerView.ViewHolder(listSoundButtonBinding.root) {
        fun bindSoundButtonToViewHolder(button: SoundButton) {
            listSoundButtonBinding.soundButton = button
            /**
             * recommended to executePendingBindings when using binding adapters with recycler view,
             * since it can be slightly faster to size the views
             */
            listSoundButtonBinding.executePendingBindings()
        }
        companion object {
            fun createFromParent(parent: ViewGroup): SoundButtonViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListSoundButtonBinding.inflate(layoutInflater,parent,false)
                return SoundButtonViewHolder(binding)
            }
        }
    }


}


// TODO: 8/22/2020 change are items the same to a ID implementation
class SoundButtonDiffCallBack : DiffUtil.ItemCallback<SoundButton>() {
    override fun areItemsTheSame(oldItem: SoundButton, newItem: SoundButton): Boolean {
        return (oldItem.shortDescription == newItem.shortDescription)
    }

    override fun areContentsTheSame(oldItem: SoundButton, newItem: SoundButton): Boolean {
        return (oldItem.imagePath == newItem.imagePath && oldItem.soundPath == newItem.soundPath)
    }

}
