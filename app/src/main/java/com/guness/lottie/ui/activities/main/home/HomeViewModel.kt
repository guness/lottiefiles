package com.guness.lottie.ui.activities.main.home

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Article
import com.guness.lottie.data.repo.ArticleRepository
import com.guness.lottie.utils.WebMetadataRetriever
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val articleRepository: ArticleRepository, private val metadataRetriever: WebMetadataRetriever) : ViewModel() {

    val articles: Flow<List<Article>>
        get() = articleRepository.articles

    suspend fun loadArticles() = withContext(Dispatchers.IO) {
        articleUrls.forEach {
            launch {
                try {
                    with(metadataRetriever.getMetadata(it)) {
                        if (isValid()) {
                            articleRepository.putArticle(Article(url = url!!, title!!, image!!))
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Error fetching url: $it")
                }
            }
        }
    }


    companion object {
        val articleUrls = listOf(
            "https://positivepsychology.com/daily-affirmations/",
            "https://www.mindtools.com/pages/article/affirmations.htm",
            "https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4814782/#idm139865532935472title",
            "https://journals.sagepub.com/doi/full/10.1177/0146167219853840",
            "https://www.pnas.org/content/112/7/1977"
        )
    }
}
