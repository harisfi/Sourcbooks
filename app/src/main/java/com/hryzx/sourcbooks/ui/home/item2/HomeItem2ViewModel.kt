package com.hryzx.sourcbooks.ui.home.item2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hryzx.sourcbooks.ui.MainActivity

class HomeItem2ViewModel : ViewModel() {
    private val _texts = MutableLiveData<String>().apply {
        value = MainActivity.tabTitles[1]
    }
    val text: LiveData<String> = _texts
}