package com.skillbox.fragment_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val listArticles: List<ArticlesList> = listOf(
        ArticlesList(
            titleArticle = R.string.title_cat_lion,
            textArticle = R.string.text_article_cat_lion,
            bgColor = R.color.cat,
            enumArticle = ArticleSection.CAT
        ),
        ArticlesList(
            titleArticle = R.string.title_cat_tiger,
            textArticle = R.string.text_article_cat_tiger,
            bgColor = R.color.cat,
            enumArticle = ArticleSection.CAT
        ),
        ArticlesList(
            titleArticle = R.string.title_cat_leopard,
            textArticle = R.string.text_article_cat_leopard,
            bgColor = R.color.cat,
            enumArticle = ArticleSection.CAT
        ),
        ArticlesList(
            titleArticle = R.string.title_dog_dog,
            textArticle = R.string.text_article_dog_dog,
            bgColor = R.color.dog,
            enumArticle = ArticleSection.DOG
        ),
        ArticlesList(
            titleArticle = R.string.title_dog_wolf,
            textArticle = R.string.text_article_dog_wolf,
            bgColor = R.color.dog,
            enumArticle = ArticleSection.DOG
        ),
        ArticlesList(
            titleArticle = R.string.title_dog_coyote,
            textArticle = R.string.text_article_dog_coyote,
            bgColor = R.color.dog,
            enumArticle = ArticleSection.DOG
        ),
        ArticlesList(
            titleArticle = R.string.title_bird_crane,
            textArticle = R.string.text_article_bird_crane,
            bgColor = R.color.bird,
            enumArticle = ArticleSection.BIRD
        ),
        ArticlesList(
            titleArticle = R.string.title_bird_falcon,
            textArticle = R.string.text_article_bird_falcon,
            bgColor = R.color.bird,
            enumArticle = ArticleSection.BIRD
        ),
        ArticlesList(
            titleArticle = R.string.title_bird_stork,
            textArticle = R.string.text_article_bird_stork,
            bgColor = R.color.bird,
            enumArticle = ArticleSection.BIRD
        )
    )

    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager2 = findViewById(R.id.viewPager2)
        val adapter = ArticleAdapter(listArticles, this)
        viewPager2.adapter = adapter

    }
}