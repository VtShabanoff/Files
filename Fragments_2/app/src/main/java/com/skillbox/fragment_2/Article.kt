package com.skillbox.fragment_2

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class Article(
    @StringRes val titleArticle: Int,
    @StringRes val textArticle: Int,
    @ColorRes val bgColor: Int,
    val articleSection: ArticleSection
)

@kotlinx.parcelize.Parcelize
enum class ArticleSection : Parcelable {
    CAT,
    DOG,
    BIRD
}