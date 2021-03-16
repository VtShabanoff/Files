package com.skillbox.viewandlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextWatcher as TextWatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onButtonEnabled()

        logInButton.setOnClickListener {
            userRegistration()
        }

    }

    private fun onButtonEnabled(){
        inputEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                logInButton.isEnabled = false
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isNotBlank() == true
                    && inputPassword.text.isNotBlank() && checkboxTermsOfUse.isChecked)
                    logInButton.isEnabled = true
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                logInButton.isEnabled = false
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isNotBlank() == true
                    && inputEmail.text.isNotBlank() && checkboxTermsOfUse.isChecked)
                    logInButton.isEnabled = true
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        checkboxTermsOfUse.setOnCheckedChangeListener { _, _ ->
            logInButton.isEnabled = inputEmail.text.isNotBlank() && inputPassword.text.isNotBlank()
                    && checkboxTermsOfUse.isChecked
        }
    }

    private fun userRegistration(){
            inputPassword.isEnabled = false
            inputEmail.isEnabled = false
            checkboxTermsOfUse.isEnabled = false
            progressBarTerms.visibility = View.VISIBLE
            logInButton.isEnabled = false
            textView.text = ""

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