package com.more9810.news_app_v_2.adapters.news_adapter

import androidx.recyclerview.widget.DiffUtil
import com.more9810.news_app_v_2.model.remote.model.news.News

class MyDiffUtilsNews(
    private val oldItem:List<News?>?,
    private val newItem: List<News?>?
): DiffUtil.Callback() {
    override fun getOldListSize() = oldItem?.size ?: 0

    override fun getNewListSize() = newItem?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem?.get(oldItemPosition)?.url == newItem?.get(newItemPosition)?.url &&
                oldItem?.get(oldItemPosition)?.author == newItem?.get(newItemPosition)?.author &&
                oldItem?.get(oldItemPosition)?.title == newItem?.get(newItemPosition)?.title &&
                oldItem?.get(oldItemPosition)?.publishedAt == newItem?.get(newItemPosition)?.publishedAt

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem?.get(oldItemPosition)?.url == newItem?.get(newItemPosition)?.url &&
                oldItem?.get(oldItemPosition)?.author == newItem?.get(newItemPosition)?.author &&
                oldItem?.get(oldItemPosition)?.title == newItem?.get(newItemPosition)?.title &&
                oldItem?.get(oldItemPosition)?.publishedAt == newItem?.get(newItemPosition)?.publishedAt
    }
}