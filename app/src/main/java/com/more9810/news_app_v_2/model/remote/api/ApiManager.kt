package com.more9810.news_app_v_2.model.remote.api

import android.util.Log
import com.more9810.news_app_v_2.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {


    private var retrofit: Retrofit? = null

    private fun initRetrofit(): Retrofit {
        if (retrofit == null) {

            val loggingInterceptor = HttpLoggingInterceptor {
                Log.e("api", it)
            }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(Constants.NEWS_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getWebService(): WebService {
        return initRetrofit().create(WebService::class.java)
    }


}