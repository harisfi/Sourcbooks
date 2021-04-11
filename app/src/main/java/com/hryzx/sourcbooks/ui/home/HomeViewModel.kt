package com.hryzx.sourcbooks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hryzx.sourcbooks.ui.MainActivity

class HomeViewModel : ViewModel() {

    private val _texts = MutableLiveData<ArrayList<String>>().apply {
        value = MainActivity.tabTitles
    }
    val text: LiveData<ArrayList<String>> = _texts
}