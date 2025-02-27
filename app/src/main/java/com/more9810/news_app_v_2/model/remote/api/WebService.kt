package com.more9810.news_app_v_2.model.remote.api

import com.more9810.news_app_v_2.model.remote.model.news.NewsResponse
import com.more9810.news_app_v_2.model.remote.model.source.SourcesResponse
import com.more9810.news_app_v_2.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET(Constants.NEWS_SOURCES_END_POINT)
    fun getSources(
        @Query("category") category: String? = null,
        @Query("apiKey") apiKey: String = Constants.NEWS_API_KYE,
    ): Call<SourcesResponse>

    @GET(Constants.NEWS_EVERYTHING_END_POINT)
    fun getNews(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = Constants.NEWS_API_KYE,
    ): Call<NewsResponse>
}