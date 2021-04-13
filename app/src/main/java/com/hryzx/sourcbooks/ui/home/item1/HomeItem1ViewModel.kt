package com.hryzx.sourcbooks.ui.home.item1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hryzx.sourcbooks.ui.MainActivity

class HomeItem1ViewModel : ViewModel() {
    private val _texts = MutableLiveData<String>().apply {
        value = MainActivity.tabTitles[0]
    }
    val text: LiveData<String> = _texts
}