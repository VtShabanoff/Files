package com.skillbox.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SuccessLogin {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: showLoginFragment()
    }

    private fun showLoginFragment(){
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.loginFragmentContainer, LoginFragment())
                .commit()
    }

    override fun onSuccessLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.loginFragmentContainer, MainFragment(), TAG_MAIN_FRAGMENT)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commit()
    }
    override fun onBackPressed() {
        val fondMainFragmentByTag = supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT)
        val countBackStack = fondMainFragmentByTag?.childFragmentManager?.backStackEntryCount
        if (countBackStack == null) super.onBackPressed()
        else fondMainFragmentByTag.childFragmentManager.popBackStack()
        if (countBackStack == 0) super.onBackPressed()
    }

    companion object{
        private const val TAG_MAIN_FRAGMENT = "tag_main_fragment"
    }



}