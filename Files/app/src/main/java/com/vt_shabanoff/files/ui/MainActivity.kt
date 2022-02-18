package com.vt_shabanoff.files.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vt_shabanoff.files.R

class MainActivity: AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFirstFragment()
    }
    private fun showFirstFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerHost, FirstFragment())
            .commit()
    }
}