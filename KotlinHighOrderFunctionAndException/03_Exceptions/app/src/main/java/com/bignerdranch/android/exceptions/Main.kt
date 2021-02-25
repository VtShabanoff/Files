package com.bignerdranch.android.exceptions

fun main() {
    try {
        val wheel = Wheel()
        wheel.setPressure(3.0)
        wheel.check()
    } catch (e: Wheel.TooLowPressure){
        println("колесо спущено, необходидо накачать до нормы")
    } catch (e: Wheel.TooHighPressure){
        println("колесо перекачено, необходимо спустить до нормы")
    } catch (e: Wheel.IncorrectPressure){
        println("некорректное значение. Экплуотация автомобиля запрещена")
    }




}