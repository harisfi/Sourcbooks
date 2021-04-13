package com.hryzx.sourcbooks.ui.home.item4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hryzx.sourcbooks.ui.MainActivity

class HomeItem4ViewModel : ViewModel() {
    private val _texts = MutableLiveData<String>().apply {
        value = MainActivity.tabTitles[3]
    }
    val text: LiveData<String> = _texts
}