package team

import `interface`.Warrior
import warriors.Captain
import warriors.General
import warriors.Soldier
import kotlin.random.Random

class Team(countWarriors: Int) {

    val listWarriors = mutableListOf<Warrior>().apply {
        for (i in 0 until countWarriors){
            when(Random.nextInt(100)){
                in 0 until 10 -> this.add(General())
                in 10 until 40 -> this.add(Captain())
                else -> this.add(Soldier())
            }
        }
    }
}