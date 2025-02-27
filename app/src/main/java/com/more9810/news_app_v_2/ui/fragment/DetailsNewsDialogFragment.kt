package com.more9810.news_app_v_2.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.more9810.news_app_v_2.databinding.FragmentDetailsNewsDialogBinding


class DetailsNewsDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailsNewsDialogBinding? = null
    private val binding get() = _binding!!
    private val args : DetailsNewsDialogFragmentArgs  by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsNewsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
    }

    private fun bindView() {
        binding.tvTitle.text = args.news.title
        Glide.with(binding.root).load(args.news.urlToImage).into(binding.imvSource)
        binding.btnTrayAgne.setOnClickListener {
            if (args.news.url == null) {
                Toast.makeText(requireContext(), "This news is wrong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.news.url)))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}