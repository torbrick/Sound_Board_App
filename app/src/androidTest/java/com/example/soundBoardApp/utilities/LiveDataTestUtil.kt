/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.soundBoardApp.utilities

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Helper method for testing LiveData objects, from
 * https://github.com/googlesamples/android-architecture-components.
 *
 * Get the value from a LiveData object. We're waiting for LiveData to emit, for 2 seconds.
 * Once we got a notification via onChanged, we stop observing.
 */



@Throws(InterruptedException::class)
fun <T> getValue(liveData: LiveData<T>): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)

//    liveData.observeForever { dataSent ->
//        data[0] = dataSent
//        latch.countDown()
//        }

    /*
    * Using explicit anonymous object construction instead of SAM conversion,
    * so that we can get a reference to the object ('this') to be able to remove it as an observer
     */
    val tempObserver = object : Observer<T>{
        override fun onChanged(dataSent: T) {
            data[0] = dataSent
            latch.countDown()
            liveData.removeObserver(this)
        }

    }

    liveData.observeForever(tempObserver)

    latch.await(2, TimeUnit.SECONDS)


    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}
