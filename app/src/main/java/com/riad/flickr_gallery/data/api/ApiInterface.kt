package com.riad.flickr_gallery.data.api

import com.riad.flickr_gallery.data.api.ApiEndPoints
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiInterface {

    @GET(ApiEndPoints.POST)
    fun getPost(): Single<ResponseBody>

}