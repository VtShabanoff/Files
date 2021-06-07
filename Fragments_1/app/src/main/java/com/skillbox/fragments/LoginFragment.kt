package com.skillbox.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class LoginFragment: Fragment() {
    private lateinit var loginButton: Button
    private lateinit var checkBoxUserAgreement: CheckBox
    private lateinit var textViewLoginFragment: TextView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var progressBarLoginFragment: ProgressBar
    var textForm = ""
    var isValid = true



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginButton = view.findViewById(R.id.buttonLogin)
        checkBoxUserAgreement = view.findViewById(R.id.checkboxLogin)
        editTextEmail = view.findViewById(R.id.emailAddressEditText)
        editTextPassword = view.findViewById(R.id.passwordEditText)
        progressBarLoginFragment = view.findViewById(R.id.progressBarLogin)

        loginButton.setOnClickListener {
            registrationUser()
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_FORM_STATE, textForm)
        outState.putBoolean(KEY_IS_VALID, isValid)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textViewLoginFragment = requireView().findViewById(R.id.textViewLogin)
            if (savedInstanceState != null){
                textViewLoginFragment.text = savedInstanceState.getString(KEY_FORM_STATE)
                loginButton.isEnabled = savedInstanceState.getBoolean(KEY_IS_VALID)
        }

    }

    override fun onStart() {
        super.onStart()
        setListenerForLoginButton()
    }



    private fun emailValid(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun buttonLoginIsEnable(s: CharSequence?){
        loginButton.isEnabled = s?.isNotBlank() == true &&
                editTextEmail.text.isNotBlank() && editTextPassword.text.isNotBlank() &&
                checkBoxUserAgreement.isChecked
    }

    private fun setListenerForLoginButton(){

        val watcherButtonLogin =
            object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    buttonLoginIsEnable(s)
                }
                override fun afterTextChanged(s: Editable?) {
                }
            }
        editTextEmail.addTextChangedListener(watcherButtonLogin)
        editTextPassword.addTextChangedListener(watcherButtonLogin)
        checkBoxUserAgreement.setOnCheckedChangeListener { _, _ ->
            loginButton.isEnabled = editTextPassword.text.isNotBlank() &&
                    editTextEmail.text.isNotBlank() &&
                    checkBoxUserAgreement.isChecked
        }
    }

    private fun registrationUser(){
        if (!emailValid(editTextEmail.text.toString())){
            textForm = "Некорректный email адрес"
            textViewLoginFragment.text = textForm
            isValid = false
            loginButton.isEnabled = isValid
            return
        }

        textViewLoginFragment.text = "Введен логин и пароль"
        editTextEmail.visibility = View.GONE
        editTextPassword.visibility = View.GONE
        checkBoxUserAgreement.visibility = View.GONE
        progressBarLoginFragment.visibility = View.VISIBLE
        loginButton.isEnabled = false

        Handler().postDelayed({
            editTextEmail.visibility = View.VISIBLE
            editTextPassword.visibility = View.VISIBLE
            checkBoxUserAgreement.visibility = View.VISIBLE
            progressBarLoginFragment.visibility = View.GONE
            loginButton.isEnabled = true
            textForm = "Регистрация прошла успешно"
            textViewLoginFragment.text = textForm
            showMainFragment()
        }, 4000)
    }

    private fun showMainFragment(){
        val mainFragment = MainFragment()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.loginFragmentContainer, mainFragment)
            ?.commit()
    }
    companion object{
        private const val KEY_FORM_STATE = "key_form_text"
        private const val KEY_IS_VALID = "key_is_valid"
    }
}