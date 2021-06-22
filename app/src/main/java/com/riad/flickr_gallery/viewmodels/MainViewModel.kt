package com.riad.flickr_gallery.viewmodels

import android.util.Log
import com.riad.flickr_gallery.base.BaseViewModel


class MainViewModel: BaseViewModel() {
    private val TAG: String = MainViewModel::class.java.getName()



    fun init() {
        Log.e(TAG, "MainViewModel init")
    }


}