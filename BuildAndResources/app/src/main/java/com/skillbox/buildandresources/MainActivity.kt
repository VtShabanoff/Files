package com.skillbox.buildandresources

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val firstLayout = findViewById<ConstraintLayout>(R.id.firstLayout)
        val landLayout = findViewById<ConstraintLayout>(R.id.landLayout)


        textView.text = """"
            Build type = ${BuildConfig.BUILD_TYPE}
            Flavor = ${BuildConfig.FLAVOR}
            Version code = ${BuildConfig.VERSION_CODE}
            Version name = ${BuildConfig.VERSION_NAME}
            Version name = ${BuildConfig.VERSION_NAME}
            Application Id = ${BuildConfig.APPLICATION_ID}
        """"
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            landLayout.setBackgroundColor(resources.getColor(R.color.color_for_second_activity))
            textView.setBackgroundColor(resources.getColor(R.color.color_for_second_activity))
        }
    }
}