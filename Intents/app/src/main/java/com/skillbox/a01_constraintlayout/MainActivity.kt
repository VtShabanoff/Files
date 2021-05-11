package com.skillbox.a01_constraintlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val KEY_MESSAGE_ERROR = "keyMessageError"
    }

    private var formState = FormState(true, "некорректный email")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.isEnabled = false
        setListenerForControlButton()
        buttonLogin.setOnClickListener {
            userRegistration()
        }

        buttonAnr.setOnClickListener {
            Thread.sleep(10000)
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        formState = savedInstanceState.getParcelable(KEY_MESSAGE_ERROR) ?: error("error")
        textViewMessage.text = formState.message
        buttonLogin.isEnabled = formState.valid
    }

        private fun isEmailValid(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
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
            textViewMessage.text = formState.message
            formState.valid = false
            buttonLogin.isEnabled = formState.valid
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
            intentSecondActivity()
            this.finish()
        }, 2000)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_MESSAGE_ERROR, formState)
    }

    private fun intentSecondActivity(){
        startActivity(Intent(this, SecondActivity::class.java))
    }

}