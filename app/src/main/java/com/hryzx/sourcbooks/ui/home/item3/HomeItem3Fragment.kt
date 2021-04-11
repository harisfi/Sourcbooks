package com.hryzx.sourcbooks.ui.home.item3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hryzx.sourcbooks.R

class HomeItem3Fragment : Fragment() {

    companion object {
        fun newInstance() = HomeItem3Fragment()
    }

    private lateinit var viewModel: HomeItem3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_item3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeItem3ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}