package com.bignerdranch.android.exceptions

class Wheel {
    var pressure: Double = 0.0
        private set

    fun setPressure(value: Double) {

            if (value > 10 || value < 0)  throw IncorrectPressure() else pressure = value

    }

    fun check(){
        when {
            pressure > 0 && pressure < 1.6 -> throw TooLowPressure()
            pressure > 2.5 && pressure < 10 -> throw TooHighPressure()
        }
    }


    class TooHighPressure: Exception("Колесо перекачено. Можно экплуатировать не полностью"){

    }
    class TooLowPressure: Exception("Колесо недокачено. Можно экплуатировать не полностью"){

    }
    class IncorrectPressure: Exception("Некорректное значение. Эксплуотация запрещена"){

    }
    
}