package com.bignerdranch.android.exceptions

class Wheel {
    var pressure: Double = 0.0
        private set

    fun setPressure(value: Double) {
        when  {
            value > 10.0 || value < 0.0 -> throw IncorrectPressure()
        }
    }


    class TooHighPressure: Exception(){

    }
    class TooLowPressure: Exception(){

    }
    class IncorrectPressure: Exception(){

    }
    
}