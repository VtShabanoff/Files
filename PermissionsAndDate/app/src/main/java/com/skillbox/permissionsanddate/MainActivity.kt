package com.skillbox.permissionsanddate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState ?: showContainerFragment()
    }

    private fun showContainerFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activityMain, ContainerFragment())
            .commit()
    }
}