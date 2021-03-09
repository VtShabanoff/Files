package com.skillbox.buildandresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        textView.text = """"
            Build type = ${BuildConfig.BUILD_TYPE}
            Flavor = ${BuildConfig.FLAVOR}
            Version code = ${BuildConfig.VERSION_CODE}
            Version name = ${BuildConfig.VERSION_NAME}
            Version name = ${BuildConfig.VERSION_NAME}
            Application Id = ${BuildConfig.APPLICATION_ID}
        """"
    }
}