package com.skillbox.multithreading.threading

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.R
import com.skillbox.multithreading.databinding.FragmentRaceConditionBinding

class RaceConditionFragment : Fragment(R.layout.fragment_race_condition) {
    private val binding by viewBinding(FragmentRaceConditionBinding::bind)
    private val buttonN: Button
        get() = binding.buttonN
    private val buttonM: Button
        get() = binding.buttonM
    private val threadCountET: EditText
        get() = binding.threadCountET
    private val incrementCountET: EditText
        get() = binding.incrementCountET
    private val textView: TextView
        get() = binding.textView

    private var value: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonN.setOnClickListener {
            textView.text = incrementWithoutSynchronization(threadCountET.text.toString().toInt(),
                incrementCountET.text.toString().toInt())
            value = 0
        }
        buttonM.setOnClickListener {
            textView.text = incrementWithSynchronization(threadCountET.text.toString().toInt(),
                incrementCountET.text.toString().toInt())
            value = 0
        }
    }

    private fun incrementWithoutSynchronization(
        threadCount: Int,
        incrementCount: Int): String{
        val expectedValue = value + threadCount * incrementCount
        val startTime = System.currentTimeMillis()
         (0 until threadCount).map {
            Thread{
                for(i in 0 until incrementCount){
                    value++
                }
            }.apply {
                start()
            }
        }.map { it.join() }
        val requestTime = System.currentTimeMillis() - startTime
        return "value = ${value}, expected=${expectedValue}, request time=${requestTime}"
    }

    private fun incrementWithSynchronization(
        threadCount: Int,
        incrementCount: Int): String{
        val expectedValue = value + threadCount * incrementCount
        val startTime = System.currentTimeMillis()
        (0 until threadCount).map {
            Thread{
                synchronized(this){
                    for(i in 0 until incrementCount){
                        value++
                    }
                }

            }.apply {
                start()
            }
        }.map { it.join() }
        val requestTime = System.currentTimeMillis() - startTime
        return "value = ${value}, expected=${expectedValue}, request time=${requestTime}"
    }

}