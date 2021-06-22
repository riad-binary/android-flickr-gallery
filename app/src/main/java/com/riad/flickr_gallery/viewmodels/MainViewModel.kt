package com.riad.flickr_gallery.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.riad.flickr_gallery.data.api.ApiClient
import com.riad.flickr_gallery.data.api.ApiInterface
import com.riad.flickr_gallery.data.api.NetworkState
import com.riad.flickr_gallery.App
import com.riad.flickr_gallery.base.BaseViewModel
import com.riad.flickr_gallery.data.models.Post
import com.riad.flickr_gallery.utils.NoConnectivityException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets

class MainViewModel: BaseViewModel() {
    private val TAG: String = MainViewModel::class.java.getName()

    val apiService : ApiInterface = ApiClient.getClient(App.instance)
    private val compositeDisposable = CompositeDisposable()

    val networkState  = MutableLiveData<NetworkState>()

    val postList =  MutableLiveData<List<Post>>()


    fun init() {
        Log.e(TAG, "MainViewModel init")
    }

    fun getPost() {
        Log.e(TAG, "getPost()")
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPost()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        Log.e(TAG, "getPost: " + Gson().toJson(it.string()))
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        if (it is NoConnectivityException) {
                            networkState.postValue(NetworkState.NO_CONNECTION)
                        } else {
                            networkState.postValue(NetworkState.ERROR)
                            Log.e(TAG, "getPost error: " + it.message)
                        }
                    }
                )
        )


    }

}