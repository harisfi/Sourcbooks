package com.hryzx.sourcbooks.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book (
    var key: String? = null,
    var title: String? = null,
    var writer: String? = null,
    var numPages: Int = 0,
    var cover: Int? = -1,
    var published: Int = 0,
    var revision: Int = 0,
    var publishers: String? = null,
    var publishPlaces: String? = null,
    var series: String? = null,
    var contributions: String? = null,
    var pagination: String? = null,
    var languages: String? = null,
    var bookmarked: Boolean = false,
    var saved: Boolean = false
) : Parcelable