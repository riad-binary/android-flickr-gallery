package com.riad.flickr_gallery.data.api

import com.riad.flickr_gallery.BuildConfig

class ApiEndPoints {

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL

        const val POST = "photos_public.gne" // https://www.flickr.com/services/feeds/photos_public.gne
    }
}