package com.skillbox.fragment_2

import android.icu.text.CaseMap
import androidx.annotation.ColorRes
import androidx.annotation.InspectableProperty
import androidx.annotation.StringRes

data class ArticlesList(
    @StringRes val titleArticle: Int,
    @StringRes val textArticle: Int,
    @ColorRes val bgColor: Int,
    val enumArticle: ArticleSection
)