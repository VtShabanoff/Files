package com.skillbox.viewandlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkboxTermsOfUse.setOnCheckedChangeListener { _, _ ->
            logInButton.isEnabled = inputEmail.text.isNotBlank() && inputPassword.text.isNotBlank()
                    && checkboxTermsOfUse.isChecked
            textView.text = ""
        }

        logInButton.setOnClickListener {
            userRegistration()
        }

    }

    private fun userRegistration(){
            inputPassword.isEnabled = false
            inputEmail.isEnabled = false
            checkboxTermsOfUse.isEnabled = false
            progressBarTerms.visibility = View.VISIBLE
            logInButton.isEnabled = false


            Handler().postDelayed({
                inputPassword.isEnabled = true
                inputPassword.setText("")
                inputEmail.isEnabled = true
                inputEmail.setText("")
                checkboxTermsOfUse.isEnabled = true
                checkboxTermsOfUse.isChecked = false
                progressBarTerms.visibility = View.GONE
                textView.text = "Регистрация прошла успешно"
            }, 4000)
    }

}