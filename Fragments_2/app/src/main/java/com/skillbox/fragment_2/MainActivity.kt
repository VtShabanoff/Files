package com.skillbox.fragment_2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.skillbox.fragment_2.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val listArticles: List<ArticlesList> = listOf(
        ArticlesList(
            titleArticle = R.string.title_cat_lion,
            textArticle = R.string.text_article_cat_lion,
            bgColor = R.color.cat,
            articleSection = ArticleSection.CAT
        ),
        ArticlesList(
            titleArticle = R.string.title_cat_tiger,
            textArticle = R.string.text_article_cat_tiger,
            bgColor = R.color.cat,
            articleSection = ArticleSection.CAT
        ),
        ArticlesList(
            titleArticle = R.string.title_cat_leopard,
            textArticle = R.string.text_article_cat_leopard,
            bgColor = R.color.cat,
            articleSection = ArticleSection.CAT
        ),
        ArticlesList(
            titleArticle = R.string.title_dog_dog,
            textArticle = R.string.text_article_dog_dog,
            bgColor = R.color.dog,
            articleSection = ArticleSection.DOG
        ),
        ArticlesList(
            titleArticle = R.string.title_dog_wolf,
            textArticle = R.string.text_article_dog_wolf,
            bgColor = R.color.dog,
            articleSection = ArticleSection.DOG
        ),
        ArticlesList(
            titleArticle = R.string.title_dog_coyote,
            textArticle = R.string.text_article_dog_coyote,
            bgColor = R.color.dog,
            articleSection = ArticleSection.DOG
        ),
        ArticlesList(
            titleArticle = R.string.title_bird_crane,
            textArticle = R.string.text_article_bird_crane,
            bgColor = R.color.bird,
            articleSection = ArticleSection.BIRD
        ),
        ArticlesList(
            titleArticle = R.string.title_bird_falcon,
            textArticle = R.string.text_article_bird_falcon,
            bgColor = R.color.bird,
            articleSection = ArticleSection.BIRD
        ),
        ArticlesList(
            titleArticle = R.string.title_bird_stork,
            textArticle = R.string.text_article_bird_stork,
            bgColor = R.color.bird,
            articleSection = ArticleSection.BIRD
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArticleAdapter(listArticles, this)
        binding.viewPager2.adapter = adapter

        setZoomOutTransformationViewPager()
        addTablayout(listArticles)
        binding.filterButton.setOnClickListener {
            //addDialog(this)
            addMultiChoiceItems(this)
        }
        binding.backButton.setOnClickListener {
            binding.viewPager2.adapter = ArticleAdapter(listArticles, this)
            addTablayout(listArticles)
        }
    }

    private fun addTablayout(articles: List<ArticlesList>){
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = articles[position].articleSection.name
            tab.setIcon(R.drawable.bear)
        }.attach()
    }

    private fun setZoomOutTransformationViewPager(){
        val minScale = 0.65f
        val minAlpha = 0.3f
        binding.viewPager2.setPageTransformer { page, position ->
            when{
                position < -1 ->{page.alpha = 0f}
                position <= 1 -> {
                    with(page){
                        scaleX = max(minScale, 1 - abs(position))
                        scaleY = max(minScale, 1 - abs(position))
                        alpha = max(minAlpha, 1 - abs(position))
                    }
                }
                else -> page.alpha = 0f
            }
        }
    }

    private fun addDialog(activity: AppCompatActivity){
        val arrayArticleSectionNames = arrayOf(
            ArticleSection.CAT.name,
            ArticleSection.DOG.name,
            ArticleSection.BIRD.name,
        )
        val dialog = AlertDialog.Builder(activity)
        with(dialog){
            setTitle("Выбери группу")
            setItems(arrayArticleSectionNames) {_, which ->
                  val filterArticleSection = listArticles.filter {
                      it.articleSection.name == arrayArticleSectionNames[which]
                  }
                    binding.viewPager2.adapter = ArticleAdapter(filterArticleSection, activity)
                    addTablayout(filterArticleSection)
                   Toast.makeText(activity, "onClick ${arrayArticleSectionNames[which]}", Toast.LENGTH_SHORT).show()
                }
        }.create().show()
    }

    private fun addMultiChoiceItems(activity: AppCompatActivity){

        val selectedItems = ArrayList<Int>()

        val articleNames = arrayOf(
            ArticleSection.CAT.name,
            ArticleSection.DOG.name,
            ArticleSection.BIRD.name,
        )

        val dialog = AlertDialog.Builder(activity)
        dialog.setMultiChoiceItems(articleNames, null) {
             _, which, isChecked ->
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                    selectedItems.add(which)
                } else if (selectedItems.contains(which)) {
                    // Else, if the item is already in the array, remove it
                    selectedItems.remove(which)
                }
            }
            .setPositiveButton("OK"){dialog, id ->
                val listSorted = ArrayList<ArticlesList>()
                selectedItems.forEach{itemChecked ->
                    listArticles.forEach { itemeList ->
                        if (itemeList.articleSection.name == articleNames[itemChecked]){
                            listSorted.add(itemeList)
                        }
                    }
                }
                binding.viewPager2.adapter = ArticleAdapter(listSorted, activity)
                addTablayout(listSorted)
            }
            .create().show()
    }

}