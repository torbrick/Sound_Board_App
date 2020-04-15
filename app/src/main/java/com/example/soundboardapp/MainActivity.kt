package com.example.soundboardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soundboardapp.ui.main.TitleFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, TitleFragment.newInstance())
//                    .commitNow()
//        }
    }
}
