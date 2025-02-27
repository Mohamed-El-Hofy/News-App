package com.more9810.news_app_v_2.model.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.more9810.news_app_v_2.R

data class Category(
    val id: String,
    @StringRes
    val name: Int,
    @DrawableRes
    val categoryImage: Int
){
    companion object{
        fun getCategory(): List<Category> = listOf(
            //businessentertainmentgeneralhealthsciencesportstechnology
            Category(id= "general",  R.string.general, R.drawable.general_1),
            Category(id= "business", R.string.business , R.drawable.business_2),
            Category(id= "entertainment", R.string.entertainment, R.drawable.entertainment_3),
            Category(id= "health",  R.string.health, R.drawable.health_4),
            Category(id= "science",  R.string.science, R.drawable.science_5),
            Category(id= "sports",  R.string.sports, R.drawable.sports_6),
            Category(id= "technology",  R.string.technology, R.drawable.technolog_7),
        )
    }
}
