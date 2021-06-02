package com.skillbox.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLoginFragment()
    }

    private fun showLoginFragment(){
        val loginFragment =
            supportFragmentManager.findFragmentById(R.id.loginFragmentContainer)
        if (loginFragment == null){
            val logFragment = LoginFragment.newInstance(LoginFragment().formState)
            supportFragmentManager.beginTransaction()
                .add(R.id.loginFragmentContainer, logFragment)
                .commit()
        }
    }


}