package com.hryzx.sourcbooks.ui.home.item1

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.databinding.FragmentHomeItem1Binding
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.ui.BookDetailActivity
import com.hryzx.sourcbooks.utils.MainImageFetcher
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import org.json.JSONObject

class HomeItem1Fragment : Fragment() {
    private var _binding:FragmentHomeItem1Binding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeItem1ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeItem1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeItem1ViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, {
            val fetcher = MainImageFetcher(it.toLowerCase(), view, binding.imgCover00, binding.imgCover01)
            fetcher.fetch()
        })
    }
}