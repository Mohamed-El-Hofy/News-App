package com.more9810.news_app_v_2.model.remote.model.source


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourcesResponse(
    @SerializedName("sources")
    val sources: List<Source?>? = null,
    @SerializedName("status")
    val status: String? = null

) : Parcelable