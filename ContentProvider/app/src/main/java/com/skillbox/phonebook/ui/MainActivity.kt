package com.skillbox.phonebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.skillbox.phonebook.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            supportFragmentManager.commit {
                replace(R.id.containerHost, ContactsFragment())
            }
    }

}