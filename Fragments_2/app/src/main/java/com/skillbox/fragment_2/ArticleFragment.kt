package com.skillbox.fragment_2

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.skillbox.fragment_2.databinding.FragmentArticleBinding

class ArticleFragment: Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewTitle.setText(requireArguments().getInt(KEY_TEXT_TITLE))
        binding.textViewArticle.setText(requireArguments().getInt(KEY_TEXT_ARTICLE))
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