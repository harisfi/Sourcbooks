package com.hryzx.sourcbooks.models

data class Book (
    var title: String = "",
    var writer: String = "",
    var numPages: Int = 0,
    var cover: Int = 0,
    var bookmarked: Boolean = false
)