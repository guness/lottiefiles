package com.guness.lottie.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.guness.lottie.data.dto.Metadata

class MetaDataCachePrefs(context: Context) {

    private val pm: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private fun setTitle(link: String, title: String) {
        pm.edit().putString(TITLE + "_$link", title).apply()
    }

    private fun getTitle(link: String): String {
        return pm.getString(TITLE + "_$link", "") ?: ""
    }

    private fun setDescription(link: String, description: String) {
        pm.edit().putString(DESCRIPTION + "_$link", description).apply()
    }

    private fun getDescription(link: String): String {
        return pm.getString(DESCRIPTION + "_$link", "") ?: ""
    }

    private fun setUrl(link: String, url: String) {
        pm.edit().putString(URL + "_$link", url).apply()
    }

    private fun getUrl(link: String): String {
        return pm.getString(URL + "_$link", "") ?: ""
    }

    private fun setImage(link: String, image: String) {
        pm.edit().putString(IMAGE + "_$link", image).apply()
    }

    private fun getImage(link: String): String {
        return pm.getString(IMAGE + "_$link", "") ?: ""
    }

    private fun setSiteName(link: String, siteName: String) {
        pm.edit().putString(SITE_NAME + "_$link", siteName).apply()
    }

    private fun getSiteName(link: String): String {
        return pm.getString(SITE_NAME + "_$link", "") ?: ""
    }

    private fun setType(link: String, type: String) {
        pm.edit().putString(TYPE + "_$link", type).apply()
    }

    private fun getType(link: String): String {
        return pm.getString(TYPE + "_$link", "") ?: ""
    }

    fun setMetadata(metadata: Metadata, url: String) {
        setTitle(url, metadata.title.toString())
        setDescription(url, metadata.description.toString())
        setImage(url, metadata.image.toString())
        setSiteName(url, metadata.siteName.toString())
        setType(url, metadata.type.toString())
        setUrl(url, metadata.url.toString())
    }

    fun getMetadata(url: String): Metadata {
        return Metadata(
            title = getTitle(url),
            description = getDescription(url),
            url = getUrl(url),
            image = getImage(url),
            siteName = getSiteName(url),
            type = getType(url)
        )
    }

    fun urlExists(url: String): Boolean {
        val title = getTitle(url)
        val description = getDescription(url)
        val image = getImage(url)

        return title.isNotEmpty() && description.isNotEmpty() && image.isNotEmpty()
    }

    companion object {
        private const val OG_PARSER = "Og_Parser"
        private const val TITLE = OG_PARSER + "_title"
        private const val DESCRIPTION = OG_PARSER + "_description"
        private const val URL = OG_PARSER + "_url"
        private const val IMAGE = OG_PARSER + "_image"
        private const val SITE_NAME = OG_PARSER + "_site_name"
        private const val TYPE = OG_PARSER + "_type"
    }
}