package com.skillbox.car

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.car.databinding.ActivityHostBinding
import com.skillbox.car.fragments.ContainerFragment

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        binding = ActivityHostBinding.inflate(layoutInflater)

        savedInstanceState ?: showContainerFragment()

    }
    private fun showContainerFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.hostActivity, ContainerFragment(), TAG_MAIN_FRAGMENT)
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager
            .findFragmentByTag(TAG_MAIN_FRAGMENT)?.let {mainFragment ->
                mainFragment.childFragmentManager.takeIf {
                    it.backStackEntryCount > 0
                }
            } ?.popBackStack() ?: super.onBackPressed()
    }

    companion object{
        const val TAG_MAIN_FRAGMENT = "tag_main_fragment"
    }
}