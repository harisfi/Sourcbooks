package com.hryzx.sourcbooks.ui.home.item3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hryzx.sourcbooks.ui.MainActivity

class HomeItem3ViewModel : ViewModel() {
    private val _texts = MutableLiveData<String>().apply {
        value = MainActivity.tabTitles[2]
    }
    val text: LiveData<String> = _texts
}