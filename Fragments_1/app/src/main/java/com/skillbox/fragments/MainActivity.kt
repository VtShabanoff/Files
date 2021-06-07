package com.skillbox.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: showLoginFragment()
    }

    private fun showLoginFragment(){
        val loginFragment =
            supportFragmentManager.findFragmentById(R.id.loginFragmentContainer)
        if (loginFragment == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.loginFragmentContainer, LoginFragment())
                .commit()
        }
    }


}