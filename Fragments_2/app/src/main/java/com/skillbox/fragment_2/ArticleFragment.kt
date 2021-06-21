package com.skillbox.fragment_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates

class ArticleFragment: Fragment() {

    lateinit var textViewArticle: TextView
    lateinit var textViewTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        textViewArticle = view.findViewById(R.id.textViewArticle)
        textViewTitle = view.findViewById(R.id.textViewTitle)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewTitle.setText(requireArguments().getInt(KEY_TEXT_TITLE))
        textViewArticle.setText(requireArguments().getInt(KEY_TEXT_ARTICLE))
        requireView().setBackgroundResource(requireArguments().getInt(KEY_COLOR))
    }

    companion object{
        private const val KEY_TEXT_TITLE = "key_text_tile"
        private const val KEY_TEXT_ARTICLE = "key_text_article"
        private const val KEY_COLOR = "key_color"

        fun newInstance(
            @StringRes textTitle: Int,
            @StringRes textArticle: Int,
            @ColorRes bgColor: Int,
        ): ArticleFragment{
            return ArticleFragment().withArguments {
                putInt(KEY_TEXT_TITLE, textTitle)
                putInt(KEY_TEXT_ARTICLE, textArticle)
                putInt(KEY_COLOR, bgColor)
            }
        }
    }
}