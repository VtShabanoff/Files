package com.skillbox.car

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.car.databinding.ActivityHostBinding
import com.skillbox.car.fragments.VehicleListFragment

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        binding = ActivityHostBinding.inflate(layoutInflater)

        savedInstanceState ?: showListVehicleFragment()

    }
    private fun showListVehicleFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.hostActivity, VehicleListFragment())
            .commit()
    }
}