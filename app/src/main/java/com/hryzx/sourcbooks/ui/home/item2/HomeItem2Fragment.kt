package com.hryzx.sourcbooks.ui.home.item2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hryzx.sourcbooks.R

class HomeItem2Fragment : Fragment() {

    companion object {
        fun newInstance() = HomeItem2Fragment()
    }

    private lateinit var viewModel: HomeItem2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_item2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeItem2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}