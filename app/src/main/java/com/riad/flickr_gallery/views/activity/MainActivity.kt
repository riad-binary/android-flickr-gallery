package com.riad.flickr_gallery.views.activity

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.riad.flickr_gallery.data.api.NetworkState
import com.riad.flickr_gallery.R
import com.riad.flickr_gallery.base.BaseDataBindingActivity
import com.riad.flickr_gallery.databinding.ActivityMainBinding
import com.riad.flickr_gallery.viewmodels.MainViewModel
import com.riad.flickr_gallery.views.adapter.PostListAdapter

class MainActivity : BaseDataBindingActivity<ActivityMainBinding, MainViewModel>() {
    private val TAG: String = MainActivity::class.java.getName()
    private lateinit var mViewModel: MainViewModel

    private lateinit var  postListAdapter: PostListAdapter


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

        viewDataBinding.recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
        postListAdapter = PostListAdapter(applicationContext)
        viewDataBinding.recyclerView.adapter = postListAdapter

        mViewModel.getPost()

        mViewModel.postList.observe(this, Observer {
            Log.d(TAG, "postList: " + it.toString())
            postListAdapter.setDataList(it)

        })

        mViewModel.networkState.observe(this, Observer {
            Log.d(TAG, it.toString())
            if(it != NetworkState.LOADING){
                viewDataBinding.progressBar.visibility = View.GONE
            } else {
                viewDataBinding.progressBar.visibility = View.VISIBLE
            }
        })




    }

}
