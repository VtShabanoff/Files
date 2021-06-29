package com.skillbox.fragment_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayoutMediator
import com.skillbox.fragment_2.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.math.max

class MainActivity : AppCompatActivity(), ItemMultiChoiceListener {
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
    lateinit var listSorted: List<ArticlesList>
    private var sortedListInteger = ArrayList<Int>()
    private val articleNames = arrayOf(
        ArticleSection.CAT.name,
        ArticleSection.DOG.name,
        ArticleSection.BIRD.name,
    )
    private var arrayCheckBoolean = booleanArrayOf(false,false,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            addAdapter(listArticles)
            setZoomOutTransformationViewPager()
            addTablayout(listArticles)

         binding.filterButton.setOnClickListener {
           ArticleDialogFragment.newInstance(sortedListInteger, arrayCheckBoolean).show(supportFragmentManager, "TAG")
        }
           binding.backButton.setOnClickListener {
            addAdapter(listArticles)
            addTablayout(listArticles)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList(KEY_SORTED_LIST, sortedListInteger)
        outState.putBooleanArray(KEY_BOOLEAN_ARRAY, arrayCheckBoolean)
        Log.d("tag", "sortedListInteger в методе onSaveInstanceState = ${sortedListInteger.size}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("tag", "sortedListInteger в методе onRestoreInstanceState = ${sortedListInteger.size}")
        sortedListInteger =
            savedInstanceState.getIntegerArrayList(KEY_SORTED_LIST) as ArrayList<Int>
        arrayCheckBoolean =
            savedInstanceState.getBooleanArray(KEY_BOOLEAN_ARRAY)!!
        listSorted = ArrayList()
        filterArticleListForMultiChoice(sortedListInteger,
            listSorted as ArrayList<ArticlesList>, articleNames)
        addAdapter(listSorted)
        addTablayout(listSorted)

    }

    private fun addTablayout(articles: List<ArticlesList>){
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = articles[position].articleSection.name
            tab.setIcon(R.drawable.bear)
        }.attach()
    }

    private fun addAdapter(list: List<ArticlesList>){
        binding.viewPager2.adapter = ArticleAdapter(list, this)
    }

    private fun filterArticleListForMultiChoice(
        sortedListInt: List<Int>,
        sortedList: ArrayList<ArticlesList>,
        arrayNames: Array<String>,
    ){
        sortedListInt.forEach{itemChecked ->
            listArticles.forEach { itemList ->
                if (itemList.articleSection.name == arrayNames[itemChecked]){
                    sortedList.add(itemList)
                    Log.d("tag", "sortedListInteger в фильтре = ${sortedListInteger.size}")
                }
            }
        }
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

    override fun onItemMultiChoice(sortedListItem: ArrayList<Int>, checkItemsBoolean: BooleanArray) {
        sortedListInteger = sortedListItem
        arrayCheckBoolean = checkItemsBoolean

        listSorted = ArrayList()
        filterArticleListForMultiChoice(sortedListInteger,
            listSorted as ArrayList<ArticlesList>, articleNames)
        addAdapter(listSorted)
        addTablayout(listSorted)
    }
    companion object{
        const val KEY_SORTED_LIST = "key_sorted_list"
        const val KEY_BOOLEAN_ARRAY = "key_sorted_list"

    }

}