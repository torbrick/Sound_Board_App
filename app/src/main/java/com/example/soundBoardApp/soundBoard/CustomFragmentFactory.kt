package com.example.soundBoardApp.soundBoard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.soundBoardApp.tools.DependencyInjectors

//class SoundBoardFragmentFactory(private val dependencyInjector : DependencyInjectors) : FragmentFactory(){
//    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        if (className == SoundBoardFragment::class.java.name) {
//            return SoundBoardFragment(dependencyInjector)
//        }
//        return super.instantiate(classLoader, className)
//    }
//}

class CustomFragmentFactory(val placeHolderStringTest: String) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {

            SoundBoardFragment::class.java -> SoundBoardFragment(placeHolderStringTest)

            else -> super.instantiate(classLoader, className)
        }
}