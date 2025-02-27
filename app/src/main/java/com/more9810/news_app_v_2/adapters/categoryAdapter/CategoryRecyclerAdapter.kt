package com.more9810.news_app_v_2.adapters.categoryAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.more9810.news_app_v_2.R
import com.more9810.news_app_v_2.databinding.ItemCategoryBinding
import com.more9810.news_app_v_2.model.local.Category

class CategoryRecyclerAdapter : RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {
    private var item: List<Category?>? = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = item?.size ?: 0
    fun setItem(newItem: List<Category?>?) {
        val diff = DiffUtil.calculateDiff(MyDiffUtilsCategory(item, newItem))
        item = newItem
        diff.dispatchUpdatesTo(this)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = item?.get(position)
        currentItem?.let { holder.bindData(it,onClickCategory) }

    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(category: Category, onClickCategory: OnClickItemListener?) {




            Glide.with(binding.imvCategory).load(category.categoryImage).error(R.drawable.ic_error)
                .into(binding.imvCategory)

            binding.root.setOnClickListener {
                if (onClickCategory == null)return@setOnClickListener
                onClickCategory.onClickItem(category,)
            }
        }

    }

    var onClickCategory: OnClickItemListener? = null

    fun interface OnClickItemListener {
        fun onClickItem(category: Category)
    }
}