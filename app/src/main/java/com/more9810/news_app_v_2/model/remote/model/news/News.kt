package com.more9810.news_app_v_2.model.remote.model.news


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.more9810.news_app_v_2.model.remote.model.source.Source
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
data class News(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("source")
    val source: Source? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null
) : Parcelable{
    fun getPublishDatAsLong(): Long?{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = publishedAt?.let { simpleDateFormat.parse(it) }
        return date?.time
    }
}