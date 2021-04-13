package com.hryzx.sourcbooks.ui.home.item3

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.databinding.FragmentHomeItem3Binding
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.ui.BookDetailActivity
import com.hryzx.sourcbooks.ui.home.item2.HomeItem2ViewModel
import com.hryzx.sourcbooks.utils.MainImageFetcher

class HomeItem3Fragment : Fragment() {
    private var _binding: FragmentHomeItem3Binding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeItem3ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeItem3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeItem3ViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, {
            val fetcher = MainImageFetcher(it.toLowerCase(), view, binding.imgCover20, binding.imgCover21)
            fetcher.fetch()
        })
    }
}