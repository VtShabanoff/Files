package com.skillbox.a02_tollbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val message = "onDestroy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickItemsMenu()
        itemSearchMenu()




    }
    private fun toast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun onClickItemsMenu(){
        toolbar.setOnMenuItemClickListener {toolbarMenu ->
            when(toolbarMenu.itemId){
                R.id.itemMenuFishDish ->{
                    startActivity(Intent(this, FishDishActivity :: class.java))
                    true
                }
                R.id.dish_2 ->{
                    toast("clicked on item dish 2")
                    true
                }
                R.id.dish_3 ->{
                    toast("clicked on item dish 3")
                    true
                }
                R.id.dish_4 ->{
                    toast("clicked on item dish 4")
                    true
                }
                else -> false
            }
        }
    }


    private fun itemSearchMenu(){
        val itemSearch = toolbar.menu.findItem(R.id.itemSearch)
        val textMainMenu = textViewMainMenu.text.toString()
        val lisTextMainMenu = textMainMenu.split(" ").toList()

        (itemSearch.actionView as SearchView).setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    fondText.isVisible = true
                    textViewMainMenu.isVisible = false
                    lisTextMainMenu.filter { it.contains(p0 ?: "", true) }
                        .joinToString()
                        .let { fondText.text = it }
                    if (p0 == ""){
                        fondText.isVisible = false
                        textViewMainMenu.isVisible = true
                    }
                    return true
                }

            }
        )


    }

    override fun onDestroy() {
        super.onDestroy()
        DebugLogger.d(tag, message)
    }

    object DebugLogger{
        fun d(tag: String, message: String){
            if (BuildConfig.DEBUG){
                Log.d(tag, message)
            }
        }
    }

}