package com.riad.flickr_gallery.data.api

import android.content.Context
import com.riad.flickr_gallery.data.api.ApiEndPoints
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ApiClient {

    private lateinit var interceptor: HttpLoggingInterceptor
    private lateinit var okHttpClient: OkHttpClient

    fun getClient(context: Context): ApiInterface {

        interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor(context))
            .addInterceptor(interceptor)
//            .addInterceptor(BasicAuthInterceptor(ApiEndPoints.AUTH_USERNAME, ApiEndPoints.AUTH_PASSWORD))
            .connectionSpecs(
                Arrays.asList(
//                ConnectionSpec.CLEARTEXT,
                ConnectionSpec.MODERN_TLS,
                ConnectionSpec.COMPATIBLE_TLS))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(null)
            .build()


        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiEndPoints.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}