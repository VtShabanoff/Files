package com.skillbox.car

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.car.fragments.CarListFragment

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        showListVehicleFragment()
    }
    private fun showListVehicleFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.hostActivity, CarListFragment())
            .commit()
    }
}