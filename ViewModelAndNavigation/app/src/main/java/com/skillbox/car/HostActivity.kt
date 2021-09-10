package com.skillbox.car

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.car.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        binding = ActivityHostBinding.inflate(layoutInflater)

    }
}