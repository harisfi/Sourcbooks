package com.hryzx.sourcbooks.ui.home.item1

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hryzx.sourcbooks.R

class HomeItem1Fragment : Fragment() {

    companion object {
        fun newInstance() = HomeItem1Fragment()
    }

    private lateinit var viewModel: HomeItem1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_item1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeItem1ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}