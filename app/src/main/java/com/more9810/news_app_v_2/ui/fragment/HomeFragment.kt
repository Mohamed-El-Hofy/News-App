package com.more9810.news_app_v_2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.more9810.news_app_v_2.adapters.categoryAdapter.CategoryRecyclerAdapter
import com.more9810.news_app_v_2.databinding.FragmentHomeBinding
import com.more9810.news_app_v_2.model.local.Category

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var adapter: CategoryRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CategoryRecyclerAdapter()
        binding.rvCategory.adapter = adapter
        adapter.setItem(Category.getCategory())
        adapter.onClickCategory = CategoryRecyclerAdapter.OnClickItemListener { category->
            val action = HomeFragmentDirections.actionHomeFragmentToNewsFragment(category.id)
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}