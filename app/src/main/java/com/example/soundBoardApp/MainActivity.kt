package com.example.soundBoardApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soundBoardApp.soundBoard.CustomFragmentFactory
import com.example.soundBoardApp.soundBoard.SoundBoardFragment


/**
 * This main activity is just a container for fragments, the host fragment
 */
class MainActivity : AppCompatActivity() {

//    private val fragmentFactory =
    // private lateinit var mainFragment: CustomNavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        supportFragmentManager.fragmentFactory = CustomFragmentFactory("DebugTestString")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, SoundBoardFragment::class.java, null)
            .commit()


    }


}


