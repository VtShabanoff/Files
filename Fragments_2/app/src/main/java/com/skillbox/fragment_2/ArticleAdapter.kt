package com.skillbox.fragment_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleAdapter(
    private val articles: List<ArticlesList>,
    activity: FragmentActivity
): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return articles.size
    }
    val list = listOf<Int>()
    override fun createFragment(position: Int): Fragment {
        val article: ArticlesList = articles[position]
       return ArticleFragment.newInstance(
            textTitle = article.titleArticle,
            textArticle = article.textArticle,
            bgColor = article.bgColor
        )
    }
}