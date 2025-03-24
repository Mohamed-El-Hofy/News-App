package com.more9810.news_app_v_2.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.more9810.news_app_v_2.adapters.news_adapter.NewsRecyclerAdapter
import com.more9810.news_app_v_2.databinding.FragmentNewsBinding
import com.more9810.news_app_v_2.model.remote.api.ApiManager
import com.more9810.news_app_v_2.model.remote.model.ErrorResponse
import com.more9810.news_app_v_2.model.remote.model.news.News
import com.more9810.news_app_v_2.model.remote.model.news.NewsResponse
import com.more9810.news_app_v_2.model.remote.model.source.Source
import com.more9810.news_app_v_2.model.remote.model.source.SourcesResponse
import com.more9810.news_app_v_2.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val args: NewsFragmentArgs by navArgs()
    private lateinit var adapter: NewsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        Log.d("more1010", "arg Category: ${args.categoryName}")
        loadNewsSources(args.categoryName)
    }

    private fun initViews() {
        adapter = NewsRecyclerAdapter()
        binding.rvNews.adapter = adapter
    }

    private fun loadNewsSources(category: String? = null) {
        handelUiStatResponse(Constants.STATE_LOADING)
        ApiManager.getWebService().getSources(category).enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>,
            ) {
                if (!response.isSuccessful) {
                    val errorResponse =
                        Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)

                    handelUiStatResponse(
                        Constants.STATE_ERROR,
                        "${errorResponse.code}\n${errorResponse.status}\n${errorResponse.message}"

                    ) {
                        loadNewsSources(category)
                    }
                    return
                }
                bindTab(response.body()?.sources)

            }

            override fun onFailure(call: Call<SourcesResponse>, throwable: Throwable) {
                handelUiStatResponse(
                    Constants.STATE_ERROR, throwable.localizedMessage ?: "Something went Wrong"
                ) {
                    loadNewsSources(category)
                }
            }

        })
    }

    private fun bindTab(sources: List<Source?>?) {
        handelUiStatResponse(Constants.STATE_SUCCESS)
        sources?.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            binding.tabLayout.addTab(tab)
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source?
                source?.id?.let { loadNews(it) }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source?
                source?.id?.let { loadNews(it) }
            }

        })
        binding.tabLayout.getTabAt(0)?.select()
    }

    private fun loadNews(sourceId: String) {
        handelUiStatResponse(Constants.STATE_LOADING)
        ApiManager.getWebService().getNews(sourceId).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>,
            ) {
                if (!response.isSuccessful) {
                    val errorResponse =
                        Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)

                    handelUiStatResponse(
                        Constants.STATE_ERROR,
                        "${errorResponse.code}\n${errorResponse.status}\n${errorResponse.message}"

                    ) {
                        loadNews(sourceId)
                    }
                    return
                }

                bindNewsItemToRecyclerView(response.body()?.newsList)

            }

            override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {
                handelUiStatResponse(
                    Constants.STATE_ERROR, throwable.localizedMessage ?: "Something went Wrong"
                ) {
                    loadNews(sourceId)
                }
            }
        })

    }

    private fun bindNewsItemToRecyclerView(newsList: List<News?>?) {
        handelUiStatResponse(Constants.STATE_SUCCESS)
        adapter.setItem(newsList)

        adapter.onItemClick = NewsRecyclerAdapter.OnClickItemListener { news ->
            val action = NewsFragmentDirections.actionNewsFragmentToDetailsNewsDialogFragment(news)
            findNavController().navigate(action)
        }
    }


    fun handelUiStatResponse(
        state: String,
        message: String? = null,
        onTryAgain: (() -> Unit)? = null,
    ) {
        when (state) {
            Constants.STATE_ERROR -> {
                binding.linearError.isVisible = true
                binding.linearLoading.isVisible = false
                binding.tvErrorMessage.text = "$message" ?: ""
                binding.btnTryAgain.setOnClickListener {
                    if (onTryAgain == null) return@setOnClickListener
                    onTryAgain.invoke()
                }
            }

            Constants.STATE_LOADING -> {
                binding.linearError.isVisible = false
                binding.linearLoading.isVisible = true
            }

            Constants.STATE_SUCCESS -> {
                binding.linearError.isVisible = false
                binding.linearLoading.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}