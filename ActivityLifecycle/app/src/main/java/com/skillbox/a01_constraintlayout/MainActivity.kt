package com.skillbox.a01_constraintlayout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private val logger = Logger()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logger.assert("On Create")
        logger.verbose("On Create")
        logger.info("On Create")
        logger.debug("On Create")
        logger.error("On Create")

        buttonLogin.isEnabled = false
        setListenerForControlButton()
        buttonLogin.setOnClickListener {
            userRegistration()
        }

        buttonAnr.setOnClickListener {
            Thread.sleep(10000)
        }
    }

    private fun isEmailValid(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onStart() {
        super.onStart()
        logger.assert("On Start")
        logger.verbose("On Start")
        logger.info("On Start")
        logger.debug("On Start")
        logger.error("On Start")
    }

    override fun onResume() {
        super.onResume()
        logger.assert("On Resume")
        logger.verbose("On Resume")
        logger.info("On Resume")
        logger.debug("On Resume")
        logger.error("On Resume")
    }

    override fun onPause() {
        super.onPause()
        logger.assert("On Pause")
        logger.verbose("On Pause")
        logger.info("On Pause")
        logger.debug("On Pause")
        logger.error("On Pause")
    }

    override fun onStop() {
        super.onStop()
        logger.assert("On Stop")
        logger.verbose("On Stop")
        logger.info("On Stop")
        logger.debug("On Stop")
        logger.error("On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.assert("On Destroy")
        logger.verbose("On Destroy")
        logger.info("On Destroy")
        logger.debug("On Destroy")
        logger.error("On Destroy")
    }

    private fun buttonEnable(p0: CharSequence?){
        buttonLogin.isEnabled = (p0?.isNotBlank() == true
                && editTextPassword.text.isNotBlank() && editTextEmailAddress.text.isNotBlank()
                && checkBoxCustomMessage.isChecked)
    }

    private fun setListenerForControlButton(){
        val buttonEnabled =
            object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    buttonEnable(p0)
                }
                override fun afterTextChanged(p0: Editable?) {}
            }

            editTextEmailAddress.addTextChangedListener(buttonEnabled)
            editTextPassword.addTextChangedListener(buttonEnabled)
            checkBoxCustomMessage.setOnCheckedChangeListener { _, _ ->
                buttonLogin.isEnabled = editTextEmailAddress.text.isNotBlank() &&
                        editTextPassword.text.isNotBlank() &&
                        checkBoxCustomMessage.isChecked
            }
    }


    private fun userRegistration(){

        if (!isEmailValid(editTextEmailAddress.text.toString())) {
        textViewMessage.text = "email address entered incorrect"
        return
        }
        editTextPassword.isEnabled = false
        editTextEmailAddress.isEnabled = false
        checkBoxCustomMessage.isChecked = false
        progressBar.visibility = View.VISIBLE
        buttonLogin.isEnabled = false
        textViewMessage.text = "Введен логин, введен пароль"

        Handler().postDelayed({
            editTextPassword.isEnabled = true
            editTextPassword.setText("")
            editTextEmailAddress.isEnabled = true
            editTextEmailAddress.setText("")
            checkBoxCustomMessage.isEnabled = true
            checkBoxCustomMessage.isChecked = false
            progressBar.visibility = View.GONE
            textViewMessage.text = "Регистрация прошла успешно"
        }, 2000)

    }


}