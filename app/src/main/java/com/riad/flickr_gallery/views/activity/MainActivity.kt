package com.riad.flickr_gallery.views.activity

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.riad.flickr_gallery.R
import com.riad.flickr_gallery.base.BaseDataBindingActivity
import com.riad.flickr_gallery.databinding.ActivityMainBinding
import com.riad.flickr_gallery.viewmodels.MainViewModel

class MainActivity : BaseDataBindingActivity<ActivityMainBinding, MainViewModel>() {
    private val TAG: String = MainActivity::class.java.getName()
    private lateinit var mViewModel: MainViewModel


    override val layoutId: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = com.riad.flickr_gallery.BR.viewModel

    override val viewModel: MainViewModel
    get (){
//        viewDataBinding!!.lifecycleOwner = this
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.init()
        return mViewModel as MainViewModel
    }

    override fun initView() {
        Log.e(TAG, "initView")

        mViewModel.getPost()

    }

}
