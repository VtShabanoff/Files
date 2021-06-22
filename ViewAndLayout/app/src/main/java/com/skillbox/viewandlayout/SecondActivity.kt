package com.skillbox.viewandlayout

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity: AppCompatActivity(R.layout.activity_second) {
    private val imageURL = "https://hsto.org/getpro/habr/post_images/8c8/10e/7e3/8c810e7e3dc46a5a677d83bed4892220.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide
            .with(this)
            .load(imageURL)
            .into(imageViewSec)

    }

}