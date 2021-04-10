package com.skillbox.a02_tollbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fish_dish.*

class FishDishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_dish)

        buttonBackToMainMenu.setOnClickListener {
            startActivity(Intent(this, MainActivity :: class.java))
        }
    }
}