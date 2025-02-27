package com.more9810.news_app_v_2.adapters.categoryAdapter

import androidx.recyclerview.widget.DiffUtil
import com.more9810.news_app_v_2.model.local.Category

class MyDiffUtilsCategory(
    private val oldItem:List<Category?>?,
    private val newItem: List<Category?>?
): DiffUtil.Callback() {
    override fun getOldListSize() = oldItem?.size ?: 0

    override fun getNewListSize() = newItem?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem?.get(oldItemPosition)?.id == newItem?.get(newItemPosition)?.id &&
                oldItem?.get(oldItemPosition)?.categoryImage == newItem?.get(newItemPosition)?.categoryImage &&
                oldItem?.get(oldItemPosition)?.name == newItem?.get(newItemPosition)?.name

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem?.get(oldItemPosition)?.id == newItem?.get(newItemPosition)?.id &&
                oldItem?.get(oldItemPosition)?.categoryImage == newItem?.get(newItemPosition)?.categoryImage &&
                oldItem?.get(oldItemPosition)?.name == newItem?.get(newItemPosition)?.name
    }
}