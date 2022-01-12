package com.guness.lottie.data.dto

import android.webkit.URLUtil

data class Metadata(
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var image: String? = null,
    var siteName: String? = null,
    var type: String? = null
) {
    fun isValid() = URLUtil.isNetworkUrl(image) && URLUtil.isNetworkUrl(url) && title?.isNotEmpty() == true
}

