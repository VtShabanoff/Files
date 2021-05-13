package com.skillbox.a01_constraintlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_deep.*

class Deep: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep)

        handleIntentData()
    }

    private fun handleIntentData(){
        intent.data?.host?.let { host ->
            textViewActivityDeepForLink.text = host
        }
        webViewActivityDeep.loadUrl("https://www.ivi.ru/movies")
    }
}