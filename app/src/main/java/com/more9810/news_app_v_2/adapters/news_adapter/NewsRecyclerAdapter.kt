package com.more9810.news_app_v_2.adapters.news_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.more9810.news_app_v_2.R
import com.more9810.news_app_v_2.databinding.ItemNewsBinding
import com.more9810.news_app_v_2.model.remote.model.news.News

class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {
    private var item: List<News?>? = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = item?.size ?: 0
    fun setItem(newItem: List<News?>?) {
        val diff = DiffUtil.calculateDiff(MyDiffUtilsNews(item, newItem))
        item = newItem
        diff.dispatchUpdatesTo(this)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item?.get(position)
        currentItem?.let { holder.bindData(it,onItemClick) }

    }

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(news: News, onItemClick: OnClickItemListener?) {
            binding.tvTitle.text = news.content
            "By: ${news.author}".also { binding.tvAuthor.text = it }

            val date = news.getPublishDatAsLong()?.let { TimeAgo.using(it) }
            binding.tvPublishDate.text = date

            Glide.with(binding.imvSource).load(news.urlToImage).error(R.drawable.ic_error)
                .into(binding.imvSource)

            binding.root.setOnClickListener {
                if (onItemClick == null)return@setOnClickListener
                onItemClick.onClickItem(news,)
            }
        }

    }

    var onItemClick: OnClickItemListener? = null

    fun interface OnClickItemListener {
        fun onClickItem(news: News, )
    }
}