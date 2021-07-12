package com.skillbox.fragment_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.skillbox.fragment_2.databinding.ActivityMainBinding
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.max

class MainActivity : AppCompatActivity(), ItemMultiChoiceListener {
    lateinit var binding: ActivityMainBinding
    private val listArticles: List<Article> = listOf(
        Article(
            titleArticle = R.string.title_cat_lion,
            textArticle = R.string.text_article_cat_lion,
            bgColor = R.color.cat,
            articleSection = ArticleSection.CAT
        ),
        Article(
            titleArticle = R.string.title_cat_tiger,
            textArticle = R.string.text_article_cat_tiger,
            bgColor = R.color.cat,
            articleSection = ArticleSection.CAT
        ),
        Article(
            titleArticle = R.string.title_cat_leopard,
            textArticle = R.string.text_article_cat_leopard,
            bgColor = R.color.cat,
            articleSection = ArticleSection.CAT
        ),
        Article(
            titleArticle = R.string.title_dog_dog,
            textArticle = R.string.text_article_dog_dog,
            bgColor = R.color.dog,
            articleSection = ArticleSection.DOG
        ),
        Article(
            titleArticle = R.string.title_dog_wolf,
            textArticle = R.string.text_article_dog_wolf,
            bgColor = R.color.dog,
            articleSection = ArticleSection.DOG
        ),
        Article(
            titleArticle = R.string.title_dog_coyote,
            textArticle = R.string.text_article_dog_coyote,
            bgColor = R.color.dog,
            articleSection = ArticleSection.DOG
        ),
        Article(
            titleArticle = R.string.title_bird_crane,
            textArticle = R.string.text_article_bird_crane,
            bgColor = R.color.bird,
            articleSection = ArticleSection.BIRD
        ),
        Article(
            titleArticle = R.string.title_bird_falcon,
            textArticle = R.string.text_article_bird_falcon,
            bgColor = R.color.bird,
            articleSection = ArticleSection.BIRD
        ),
        Article(
            titleArticle = R.string.title_bird_stork,
            textArticle = R.string.text_article_bird_stork,
            bgColor = R.color.bird,
            articleSection = ArticleSection.BIRD
        )
    )
    private var currentTypes = ArrayList<ArticleSection>()
    private lateinit var filterArticles: ArrayList<Article>
    private var _pager: ViewPager2? = null
    private val pager
        get() = _pager!!
    //пробный коммент для git

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _pager = binding.viewPager2

        binding.filterButton.setOnClickListener {
            showDialogFragment()
        }

        updatePagerAdapter(listArticles)
        setZoomOutTransformationViewPager(pager)
        updateTablayout(listArticles)

        binding.backButton.setOnClickListener {
            updatePagerAdapter(listArticles)
            updateTablayout(listArticles)
        }
    }

    private fun showDialogFragment() {
        if (currentTypes.isNotEmpty()) {
            ArticleDialogFragment.newInstance(currentTypes).show(supportFragmentManager, "TAG")
        } else {
            ArticleDialogFragment().show(supportFragmentManager, "TAG")
        }
    }

    private fun updateTablayout(articles: List<Article>) {
        TabLayoutMediator(binding.tabLayout, pager) { tab, position ->
            tab.text = articles[position].articleSection.name
            tab.setIcon(R.drawable.bear)
        }.attach()
    }

    private fun updatePagerAdapter(listArticles: List<Article>) {
        val adapter = ArticleAdapter(listArticles, this)
        pager.adapter = adapter
    }

    private fun filterArticleListForMultiChoice(
        currentTypes: ArrayList<ArticleSection>,
    ) {
        filterArticles = ArrayList()
        currentTypes.forEach { type ->
            listArticles.forEach { article ->
                if (article.articleSection == type) {
                    filterArticles.add(article)

                }
            }
        }
        updatePagerAdapter(filterArticles)
        updateTablayout(filterArticles)
    }

    private fun setZoomOutTransformationViewPager(viewPager: ViewPager2) {
        val minScale = 0.65f
        val minAlpha = 0.3f
        viewPager.setPageTransformer { page, position ->
            when {
                position < -1 -> {
                    page.alpha = 0f
                }
                position <= 1 -> {
                    with(page) {
                        scaleX = max(minScale, 1 - abs(position))
                        scaleY = max(minScale, 1 - abs(position))
                        alpha = max(minAlpha, 1 - abs(position))
                    }
                }
                else -> page.alpha = 0f
            }
        }
    }

    companion object {
        const val KEY_CURRENT_TYPE = "key_current_type"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_CURRENT_TYPE, currentTypes)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        currentTypes =
            savedInstanceState.getParcelableArrayList<ArticleSection>(KEY_CURRENT_TYPE) as ArrayList<ArticleSection>

        if (currentTypes.isNotEmpty()) {
            filterArticleListForMultiChoice(currentTypes)
        } else {
            updatePagerAdapter(listArticles)
            updateTablayout(listArticles)
        }
    }

    override fun onItemMultiChoice(types: ArrayList<ArticleSection>) {
        currentTypes = types
        filterArticleListForMultiChoice(currentTypes)
    }

}