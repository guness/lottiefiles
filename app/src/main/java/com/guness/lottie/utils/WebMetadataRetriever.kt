package com.guness.lottie.utils

import android.content.Context
import com.guness.lottie.data.dto.Metadata
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import javax.inject.Inject

// TODO: refactor and create a library for this purpose
class WebMetadataRetriever @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPrefs: MetaDataCachePrefs = MetaDataCachePrefs(context)

    @Throws
    suspend fun getMetadata(url: String): Metadata {
        return fetchContent(url)
    }

    private suspend fun fetchContent(url: String) = withContext(Dispatchers.IO) {
        val link = constructUrl(url)

        if (sharedPrefs.urlExists(link)) {
            return@withContext sharedPrefs.getMetadata(link)
        }
        val metadata = Metadata()

        val response = Jsoup.connect(link)
            .ignoreContentType(true)
            .timeout(TIMEOUT)
            .followRedirects(true)
            .execute()

        val doc = withContext(Dispatchers.Default) {
            response.parse()
        }

        val tags = doc.select(DOC_SELECT_QUERY)

        tags.forEach { tag ->
            when (tag.attr(PROPERTY)) {
                OG_IMAGE -> metadata.image = tag.attr(OPEN_GRAPH_KEY)
                OG_DESCRIPTION -> metadata.description = tag.attr(OPEN_GRAPH_KEY)
                OG_URL -> metadata.url = tag.attr(OPEN_GRAPH_KEY)
                OG_TITLE -> metadata.title = tag.attr(OPEN_GRAPH_KEY)
                OG_SITE_NAME -> metadata.siteName = tag.attr(OPEN_GRAPH_KEY)
                OG_TYPE -> metadata.type = tag.attr(OPEN_GRAPH_KEY)
            }
        }

        metadata.also {
            if (it.isValid()) {
                sharedPrefs.setMetadata(it, link)
            }
        }
    }

    private fun constructUrl(url: String): String {
        val builder = StringBuilder(url)
        if (builder.isEmpty()) {
            throw IllegalStateException("URL is empty")
        }

        if (builder.startsWith(HTTPS_PREFIX)) {
            builder.delete(0, HTTPS_PREFIX.length)
        }

        if (builder.startsWith(HTTP_PREFIX)) {
            builder.delete(0, HTTP_PREFIX.length)
        }

        builder.insert(0, HTTPS_PREFIX)

        return builder.toString()
    }

    companion object {

        private const val HTTPS_PREFIX = "https://"
        private const val HTTP_PREFIX = "http://"

        private const val TIMEOUT = 20000
        private const val DOC_SELECT_QUERY = "meta[property^=og:]"
        private const val OPEN_GRAPH_KEY = "content"
        private const val PROPERTY = "property"
        private const val OG_IMAGE = "og:image"
        private const val OG_DESCRIPTION = "og:description"
        private const val OG_URL = "og:url"
        private const val OG_TITLE = "og:title"
        private const val OG_SITE_NAME = "og:site_name"
        private const val OG_TYPE = "og:type"
    }
}
