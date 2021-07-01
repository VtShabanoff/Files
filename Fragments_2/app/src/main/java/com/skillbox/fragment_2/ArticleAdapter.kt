package com.skillbox.fragment_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleAdapter(
    private val articles: List<Article>,
    activity: FragmentActivity
): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return articles.size
    }
    override fun createFragment(position: Int): Fragment {
        val article: Article = articles[position]
       return ArticleFragment.newInstance(
            textTitle = article.titleArticle,
            textArticle = article.textArticle,
            bgColor = article.bgColor
        )
    }
}